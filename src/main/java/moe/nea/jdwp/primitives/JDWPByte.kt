@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPSingleState
import moe.nea.jdwp.JDWPWriter

class JDWPByte() : JDWPSingleState<Byte>() {
    constructor(value: Byte) : this() {
        this.value = value
    }

    override fun read(reader: JDWPReader) {
        value = reader.consume(1)[0].toByte()
    }

    override fun write(writer: JDWPWriter) {
        writer.append(ubyteArrayOf(value!!.toUByte()))
    }
}