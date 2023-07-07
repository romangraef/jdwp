@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPElement
import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPWriter

object JDWPHandshake : JDWPElement {
    private val handshakeText = "JDWP-Handshake".encodeToByteArray().asUByteArray()
    override fun read(reader: JDWPReader) {
        require(reader.consume(handshakeText.size).contentEquals(handshakeText))
    }

    override fun write(writer: JDWPWriter) {
        writer.append(handshakeText)
    }
}