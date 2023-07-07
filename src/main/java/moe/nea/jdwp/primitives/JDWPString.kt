@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPSingleState
import moe.nea.jdwp.JDWPWriter

class JDWPString() : JDWPSingleState<String>() {
    constructor(value: String) : this() {
        this.value = value
    }

    private val sizeDelegate = JDWPInt()
    override fun read(reader: JDWPReader) {
        sizeDelegate.read(reader)
        value = reader.consume(sizeDelegate.value!!).toByteArray().decodeToString()
    }

    override fun write(writer: JDWPWriter) {
        val encoded = value!!.encodeToByteArray().toUByteArray()
        sizeDelegate.value = encoded.size
        sizeDelegate.write(writer)
        writer.append(encoded)
    }
}