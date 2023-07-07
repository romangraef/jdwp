@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPSingleState
import moe.nea.jdwp.JDWPWriter

class JDWPBoolean() : JDWPSingleState<Boolean>() {
    constructor(value: Boolean) : this() {
        this.value = value
    }

    override fun read(reader: JDWPReader) {
        value = reader.consume(1)[0] != 0.toUByte()
    }

    override fun write(writer: JDWPWriter) {
        writer.append(ubyteArrayOf(if (value!!) 1.toUByte() else 0.toUByte()))
    }
}