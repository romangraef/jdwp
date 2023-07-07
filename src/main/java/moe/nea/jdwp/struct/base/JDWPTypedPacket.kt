@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.struct.base

import moe.nea.jdwp.*

class JDWPTypedPacket<T : JDWPPayload>(var contents: T, header: PacketHeader = PacketHeader()) : JDWPPacket(header) {
    override fun read(reader: JDWPReader) {
        super.read(reader)
        contents.read(ArrayBackedJDWPReader(_contents, reader.sizes))
    }

    override fun write(writer: JDWPWriter) {
        val payloadWriter = ArrayBackedJDWPWriter(writer.sizes)
        contents.write(payloadWriter)
        _contents = payloadWriter.getResult()
        super.write(writer)
    }
}