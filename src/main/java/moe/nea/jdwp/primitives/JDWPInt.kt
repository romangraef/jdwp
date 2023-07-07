@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPSingleState
import moe.nea.jdwp.JDWPWriter

class JDWPInt() : JDWPSingleState<Int>() {
    constructor(value: Int) : this() {
        this.value = value
    }

    override fun read(reader: JDWPReader) {
        val bytes = reader.consume(4)
        value = ((bytes[0].toInt() shl 24) or (bytes[1].toInt() shl 16)
                or (bytes[2].toInt() shl 8) or (bytes[3].toInt()))
    }

    override fun write(writer: JDWPWriter) {
        val value = this.value!!
        writer.append(
            ubyteArrayOf(
                (value shr 24).toUByte(),
                (value shr 16).toUByte(),
                (value shr 8).toUByte(),
                (value).toUByte(),
            )
        )
    }
}