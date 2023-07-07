@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPSingleState
import moe.nea.jdwp.JDWPWriter

class JDWPString : JDWPSingleState<String>() {
    private val sizeDelegate = JDWPInt()
    override fun read(reader: JDWPReader) {
        sizeDelegate.read(reader)
        value = reader.consume(sizeDelegate.value!!).toByteArray().decodeToString()
    }

    override fun write(writer: JDWPWriter) {
        sizeDelegate.value = value!!.length
        sizeDelegate.write(writer)
        writer.append(value!!.encodeToByteArray().toUByteArray())
    }
}