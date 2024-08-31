package moe.nea

import moe.nea.jdwp.JDWPConnection
import moe.nea.jdwp.JDWPPacketStore
import moe.nea.jdwp.struct.virtualmachine.AllClassesWithGeneric
import moe.nea.jdwp.struct.virtualmachine.Version
import java.io.File
import java.net.InetSocketAddress

fun main() {
	val connection = JDWPConnection.connect(
		InetSocketAddress("127.0.0.1", 5005), JDWPPacketStore.ofPackets()
	)
	val versionRequest = connection.sendCommand(Version())
	val versionReply = connection.awaitReplyBlocking(versionRequest)
	val generic = connection.awaitReplyBlocking(connection.sendCommand(AllClassesWithGeneric()))
	File("classes.txt").writeText(generic.classesElements.joinToString("\n") { it.genericSignature + " " + it.signature })
}