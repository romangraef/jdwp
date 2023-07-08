@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp

import moe.nea.jdwp.primitives.JDWPHandshake
import moe.nea.jdwp.struct.base.*
import moe.nea.jdwp.struct.virtualmachine.IDSizes
import java.net.InetSocketAddress
import java.net.Socket

class JDWPConnection private constructor(
    val reader: JDWPReader,
    val writer: JDWPWriter,
    val packetStore: JDWPPacketStore
) {
    companion object {
        /**
         * Create a JDWP connection from an initialized reader and writer (meaning the handshake was already sent)
         */
        fun fromInitialized(reader: JDWPReader, writer: JDWPWriter, packetStore: JDWPPacketStore) =
            JDWPConnection(reader, writer, packetStore)

        fun connect(
            inetSocketAddress: InetSocketAddress,
            packetStore: JDWPPacketStore,
            initSizes: Boolean = true
        ): JDWPConnection {
            val socket = Socket()
            socket.connect(inetSocketAddress)
            val writer = JDWPOutputStreamWriter(socket.getOutputStream())
            JDWPHandshake.write(writer)
            writer.flush()
            val reader = JDWPInputStreamReader(socket.getInputStream())
            JDWPHandshake.read(reader)
            val connection = JDWPConnection(reader, writer, packetStore)
            if (initSizes) {
                val r = connection.sendCommand(IDSizes())
                val idSizes = r.match(connection.receiveCommandOrReply())
                    ?: error("Server responded out of sync instead of with id sizes during init")
                connection.setSizes(JDWPIDSizes().also { it.setFrom(idSizes) })
            }
            return connection
        }
    }

    fun setSizes(sizes: JDWPIDSizes) {
        reader.sizes.setFrom(sizes)
        writer.sizes.setFrom(sizes)
    }

    private var replyId = 0
    private var awaitingRepliesFromServer = mutableMapOf<Int, JDWPReplyPayload>()

    data class ReplyToken<N : JDWPReplyPayload> internal constructor(val replyId: Int) {
        fun match(packet: JDWPPacket): N? {
            if (packet is JDWPTypedPacket<*> && packet.header.isReply && packet.header.replyId == replyId) {
                return packet.contents as N
            }
            return null
        }
    }

    fun <T : JDWPCommandPayload<N>, N : JDWPReplyPayload> sendCommand(payload: T): ReplyToken<N> {
        val packet = JDWPTypedPacket(payload)
        packet.header.replyId = replyId++
        packet.header.commandSetId = payload.commandSetId
        packet.header.commandId = payload.commandId
        awaitingRepliesFromServer[packet.header.replyId] = payload.reply
        packet.write(writer)
        return ReplyToken(packet.header.replyId)
    }


    fun <R : JDWPReplyPayload> sendReply(replyId: Int, reply: R) {
        val packet = JDWPTypedPacket(reply)
        packet.header.replyId = replyId
        packet.header.isReply = true
        packet.write(writer)
    }

    fun <T : JDWPCommandPayload<R>, R : JDWPReplyPayload> sendReply(inReplyTo: JDWPTypedPacket<T>, reply: R) {
        sendReply(inReplyTo.header.replyId, reply)
    }

    fun <T : JDWPCommandPayload<*>> sendErrorReply(inReplyTo: JDWPTypedPacket<T>, errorCode: JDWPErrorCode) {
        sendErrorReply(inReplyTo.header.replyId, errorCode.tag)
    }

    fun sendErrorReply(replyId: Int, errorCode: Short) {
        val packet = JDWPUntypedPacket()
        packet.header.replyId = replyId
        packet.header.commandOrErrorCode = errorCode
        packet.header.isReply = true
    }


    /**
     * Consider using [sendCommand] and [sendReply] instead.
     * @param setReplyId sets the reply id of the packet to be aligned with the same counter used by [sendCommand]. Has no effect if this is a reply packet, needs to be set by the user.
     */
    fun sendPacket(packet: JDWPPacket, setReplyId: Boolean = true) {
        if (setReplyId) {
            packet.header.replyId = replyId++
        }
        if (!packet.header.isReply) {
            val payloadType = packetStore.makeUninitializedPayload(packet.header.commandSetId, packet.header.commandId)
            if (payloadType != null) {
                awaitingRepliesFromServer[packet.header.replyId] = payloadType.reply
            }
        }
        packet.write(writer)
    }

    fun <N : JDWPReplyPayload> awaitReplyBlocking(replyToken: ReplyToken<N>): N {
        return replyToken.match(receiveCommandOrReply())!!
    }

    fun receiveCommandOrReply(): JDWPPacket {
        val header = PacketHeader()
        header.read(reader)
        val extra = reader.consume(header.length - PacketHeader.PACKET_HEADER_SIZE)
        if (header.isReply) {
            val replyModel = awaitingRepliesFromServer.remove(header.replyId)
            if (replyModel != null) {
                if (header.errorCode == JDWPErrorCode.NONE) {
                    val extraReader = ArrayBackedJDWPReader(extra, reader.sizes)
                    replyModel.read(extraReader)
                    extraReader.assertConsumed()
                }
                return JDWPTypedPacket(replyModel, header)
            } else {
                return JDWPUntypedPacket(header).also { it.contents = extra }
            }
        } else {
            val commandModel = packetStore.makeUninitializedPayload(header.commandSetId, header.commandId)
            if (commandModel != null) {
                val extraReader = ArrayBackedJDWPReader(extra, reader.sizes)
                commandModel.read(extraReader)
                extraReader.assertConsumed()
                return JDWPTypedPacket(commandModel, header)
            } else {
                return JDWPUntypedPacket(header).also { it.contents = extra }
            }
        }
    }

}