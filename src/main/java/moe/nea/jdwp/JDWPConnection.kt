@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp

import moe.nea.jdwp.primitives.JDWPHandshake
import moe.nea.jdwp.struct.base.*
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

        fun connect(inetSocketAddress: InetSocketAddress, packetStore: JDWPPacketStore) {
            val socket = Socket(inetSocketAddress.address, inetSocketAddress.port)
            val reader = JDWPInputStreamReader(socket.getInputStream())
            val writer = JDWPOutputStreamWriter(socket.getOutputStream())
            JDWPHandshake.write(writer)
            JDWPHandshake.read(reader)
            val connection = JDWPConnection(reader, writer, packetStore)

        }
    }

    fun setSizes(sizes: JDWPIDSizes) {
        reader.sizes.setFrom(sizes)
        writer.sizes.setFrom(sizes)
    }

    private var replyId = 0
    private var awaitingRepliesFromServer = mutableMapOf<Int, JDWPReplyPayload>()

    fun <T : JDWPCommandPayload<*>> sendCommand(payload: T) {
        val packet = JDWPTypedPacket(payload)
        packet.header.replyId = replyId++
        packet.header.commandSetId = payload.commandSetId
        packet.header.commandId = payload.commandId
        awaitingRepliesFromServer[packet.header.replyId] = payload.reply
        packet.write(writer)
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

    fun receiveCommandOrReply(): JDWPPacket {
        val header = PacketHeader()
        header.read(reader)
        val extra = reader.consume(header.length - PacketHeader.PACKET_HEADER_SIZE)
        return if (header.isReply) {
            val replyModel = awaitingRepliesFromServer.remove(header.replyId)
            if (replyModel != null) {
                if (header.errorCode != JDWPErrorCode.NONE) {
                    val extraReader = ArrayBackedJDWPReader(extra, reader.sizes)
                    replyModel.read(extraReader)
                    extraReader.assertConsumed()
                }
                JDWPTypedPacket(replyModel, header)
            } else {
                JDWPUntypedPacket(header).also { it.contents = extra }
            }
        } else {
            val commandModel = packetStore.makeUninitializedPayload(header.commandSetId, header.commandId)
            if (commandModel != null) {
                val extraReader = ArrayBackedJDWPReader(extra, reader.sizes)
                commandModel.read(extraReader)
                extraReader.assertConsumed()
                JDWPTypedPacket(commandModel, header)
            } else {
                JDWPUntypedPacket(header).also { it.contents = extra }
            }
        }
    }

}