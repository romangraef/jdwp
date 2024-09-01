package moe.nea.jdwptrans

import moe.nea.jdwp.JDWPConnection
import moe.nea.jdwp.JDWPPacketStore
import moe.nea.jdwp.base.JDWPPacket
import moe.nea.jdwp.base.JDWPPayload
import moe.nea.jdwp.base.JDWPTypedPacket
import moe.nea.jdwp.ofAllPackets
import moe.nea.jdwp.struct.virtualmachine.AllClassesReply
import moe.nea.jdwp.struct.virtualmachine.AllClassesWithGeneric
import moe.nea.jdwp.struct.virtualmachine.AllClassesWithGenericReply
import moe.nea.jdwp.struct.virtualmachine.Version
import net.fabricmc.mappingio.MappingReader
import net.fabricmc.mappingio.tree.MemoryMappingTree
import java.io.File
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.nio.file.Path
import kotlin.concurrent.thread
import kotlin.system.exitProcess

fun writeClasses() {
	val connection = JDWPConnection.connect(
		InetSocketAddress("127.0.0.1", 5050), JDWPPacketStore.ofPackets()
	)
	val versionRequest = connection.sendCommand(Version())
	val versionReply = connection.awaitReplyBlocking(versionRequest)
	val generic = connection.awaitReplyBlocking(connection.sendCommand(AllClassesWithGeneric()))
	File("classes.txt").writeText(generic.classesElements.joinToString("\n") { it.genericSignature + " " + it.signature })
	exitProcess(0)
}

fun main() {
	val server = ServerSocket(5050)
	thread(start = true) { writeClasses() }
	while (true) {
		val down = server.accept()
		val up = Socket()
		up.connect(InetSocketAddress("localhost", 5006))
		JDWPRemapServer(down, up, JDWPPacketStore.ofAllPackets()).start()
	}
}

class JDWPRemapServer(down: Socket, up: Socket, packetStore: JDWPPacketStore) : JDWPTransServer(down, up, packetStore) {
	// TODO: do something
	val mappingIO = kotlin.run {
		val tree = MemoryMappingTree()
		MappingReader.read(
			Path.of("/home/nea/.gradle/caches/fabric-loom/1.21/net.fabricmc.yarn.1_21.1.21+build.2-v2/mappings.tiny"),
			tree)
		tree
	}
	val downNamespace = mappingIO.getNamespaceId("named")
	val upNamespace = mappingIO.getNamespaceId("intermediary")


	override fun mapPacket(packet: JDWPPacket, dir: Dir): JDWPPacket {
		if (packet is JDWPTypedPacket<*>)
			mapPacketContent(packet.contents, dir)
		return packet
	}

	fun mapPacketContent(packet: JDWPPayload, dir: Dir) {
		when (packet) {
			is AllClassesReply -> {
				packet.classesElements.forEach {
					it.signature = mapClass(Signature.parse(it.signature), dir).toString()
				}
			}

			is AllClassesWithGenericReply -> {
				packet.classesElements.forEach {
					it.signature = mapClass(Signature.parse(it.signature), dir).toString()
					if (it.genericSignature.isNotEmpty())
						it.genericSignature = mapClass(Signature.parseGeneric(it.genericSignature), dir).toString()
				}
			}
		}
	}

	fun fromNs(dir: Dir) = when (dir) {
		Dir.DOWNTOUP -> downNamespace
		Dir.UPTODOWN -> upNamespace
	}

	fun toNs(dir: Dir) = when (dir) {
		Dir.DOWNTOUP -> upNamespace
		Dir.UPTODOWN -> downNamespace
	}


	private fun <T : Signature> mapClass(signature: T, dir: Dir): T {
		return when (signature) {
			is Signature.ClassSignature -> Signature.ClassSignature(
				mapClass(signature.typeParameters, dir),
				mapClass(signature.superClassSignature, dir),
				signature.superInterfaceSignatures.map { mapClass(it, dir) }
			)

			is Signature.ArrayTypeSignature -> Signature.ArrayTypeSignature(mapClass(signature.component, dir))
			is Signature.ClassTypeSignature -> {
				val baseName = signature.packageSpecifier.toString() + signature.clazz.name
				val lambdaSuffix = "\$\$Lambda"
				val isLambda = baseName.endsWith(lambdaSuffix)
				val newName = mappingIO.mapClassName(
					if (isLambda) baseName.dropLast(lambdaSuffix.length) else baseName,
					fromNs(dir), toNs(dir)) + if (isLambda) lambdaSuffix else ""
				val parts = newName.split("/")
				val packageParts = parts.asSequence().take(parts.size - 1).map { Signature.Identifier(it) }.toList()
				Signature.ClassTypeSignature(
					Signature.PackageSpecifier(packageParts),
					Signature.SimpleClassTypeSignature(
						Signature.Identifier(parts.last()),
						mapClass(signature.clazz.typeArguments, dir)),
					signature.appendages.map { mapClass(it, dir) }
				)
			}

			is Signature.PackageSpecifier -> error("Cannot map package name alone")
			is Signature.SimpleClassTypeSignature -> Signature.SimpleClassTypeSignature(
				signature.name,
				mapClass(signature.typeArguments, dir)
			)

			is Signature.TypeArgumentReference -> Signature.TypeArgumentReference(
				signature.wildCardIndicator,
				mapClass(signature.bound, dir)
			)

			is Signature.TypeArguments -> Signature.TypeArguments(signature.list.map { mapClass(it, dir) })
			is Signature.TypeParameter -> Signature.TypeParameter(
				signature.identifier,
				signature.classBound?.let { mapClass(it, dir) },
				signature.interfaceBound.map { mapClass(it, dir) }
			)

			is Signature.TypeParameters -> Signature.TypeParameters(signature.parameters.map { mapClass(it, dir) })
			else -> signature
		} as T
	}
}