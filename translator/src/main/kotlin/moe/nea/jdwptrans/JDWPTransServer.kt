package moe.nea.jdwptrans

import moe.nea.jdwp.ArrayBackedJDWPReader
import moe.nea.jdwp.JDWPConnection
import moe.nea.jdwp.JDWPInputStreamReader
import moe.nea.jdwp.JDWPOutputStreamWriter
import moe.nea.jdwp.JDWPPacketStore
import moe.nea.jdwp.base.JDWPPacket
import moe.nea.jdwp.base.JDWPReplyPayload
import moe.nea.jdwp.base.JDWPTypedPacket
import moe.nea.jdwp.base.JDWPUntypedPacket
import moe.nea.jdwp.base.PacketHeader
import moe.nea.jdwp.ofAllPackets
import moe.nea.jdwp.primitives.JDWPHandshake
import moe.nea.jdwp.struct.virtualmachine.AllClassesWithGeneric
import moe.nea.jdwp.struct.virtualmachine.IDSizesReply
import moe.nea.jdwp.struct.virtualmachine.Version
import java.io.File
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.util.Collections
import kotlin.concurrent.thread
import kotlin.system.exitProcess


abstract class JDWPTransServer(
	val down: Socket,
	val up: Socket,
	val packetStore: JDWPPacketStore,
) {
	val downWrite = JDWPOutputStreamWriter(down.getOutputStream())
	val downRead = JDWPInputStreamReader(down.getInputStream())
	val upWrite = JDWPOutputStreamWriter(up.getOutputStream())
	val upRead = JDWPInputStreamReader(up.getInputStream())
	val threadGroup = ThreadGroup("JDWPTransServer")
	val downThread = Thread(threadGroup, {
		JDWPHandshake.read(upRead)
		JDWPHandshake.write(downWrite)
		while (true)
			pushDownToUpOnce()
	}, "Down Thread")
	val upThread = Thread(threadGroup, {
		JDWPHandshake.read(downRead)
		JDWPHandshake.write(upWrite)
		while (true)
			pushUpToDownOnce()
	}, "Up Thread")


	fun start() {
		upThread.start()
		downThread.start()
	}

	val downReplyIds: MutableMap<Int, JDWPReplyPayload> =
		Collections.synchronizedMap(mutableMapOf())
	val upReplyIds: MutableMap<Int, JDWPReplyPayload> =
		Collections.synchronizedMap(mutableMapOf())

	fun pushDownToUpOnce() {
		println("Trying to push down to up")
		val packet = readPacketG(downRead, downReplyIds, upReplyIds, packetStore, Dir.DOWNTOUP)
		wrapPacket(packet, Dir.DOWNTOUP)
		packet.write(upWrite)
	}

	fun pushUpToDownOnce() {
		println("Trying to push up to down")
		val packet = readPacketG(upRead, upReplyIds, downReplyIds, packetStore, Dir.UPTODOWN)
		wrapPacket(packet, Dir.UPTODOWN)
		packet.write(downWrite)
	}

	abstract fun mapPacket(packet: JDWPPacket, dir: Dir): JDWPPacket

	fun wrapPacket(packet: JDWPPacket, dir: Dir): JDWPPacket {
		if (packet is JDWPTypedPacket<*> && packet.contents is IDSizesReply) {
			val sizes: IDSizesReply = packet.contents as IDSizesReply
			upWrite.sizes.setFrom(sizes)
			upRead.sizes.setFrom(sizes)
			downWrite.sizes.setFrom(sizes)
			downRead.sizes.setFrom(sizes)
		}
		return mapPacket(packet, dir)
	}

	enum class Dir {
		DOWNTOUP,
		UPTODOWN,
	}

	companion object {
		private fun readPacketG(
			reader: JDWPInputStreamReader,
			saveReplies: MutableMap<Int, JDWPReplyPayload>,
			loadReplies: MutableMap<Int, JDWPReplyPayload>,
			packetStore: JDWPPacketStore, dir: Dir
		): JDWPPacket {
			val header = PacketHeader()
			header.read(reader)
			val array = reader.consume(header.length - PacketHeader.PACKET_HEADER_SIZE)
			val extra = ArrayBackedJDWPReader(array, reader.sizes)
			// TODO: check error code for replies
			val packet = if (header.isReply) {
				val reply = loadReplies.remove(header.replyId)
				val packet = if (reply == null) {
					println("$dir: Before untyped reply: ${header.replyId}")
					JDWPUntypedPacket(header).also {
						it.contents = array
					}
				} else {
					println("$dir: Before typed reply: ${reply.javaClass}")
					reply.read(extra)
					JDWPTypedPacket(reply, header)
				}
				packet
			} else {
				val command = packetStore.makeUninitializedPayload(header.commandSetId, header.commandId)
				val packet =
					if (command == null) {
						println("$dir: Before untyped command: ${header.commandSetId} ${header.commandId}")
						JDWPUntypedPacket(header).also {
							it.contents = array
						}
					} else {
						println("$dir: Before typed command: ${command.javaClass}")
						saveReplies[header.replyId] = command.reply
						command.read(extra)
						JDWPTypedPacket(command, header)
					}
				packet
			}
			return packet
		}
	}
}