@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPSingleState
import moe.nea.jdwp.JDWPWriter

class JDWPLong() : JDWPSingleState<Long>() {
    constructor(value: Long) : this() {
        this.value = value
    }

    override fun read(reader: JDWPReader) {
        val bytes = reader.consume(8)
        value = ((bytes[0].toLong() shl 56)
                or (bytes[1].toLong() shl 48)
                or (bytes[2].toLong() shl 40)
                or (bytes[3].toLong() shl 32)
                or (bytes[4].toLong() shl 24)
                or (bytes[5].toLong() shl 16)
                or (bytes[6].toLong() shl 8)
                or (bytes[7].toLong() shl 0))

    }

    override fun write(writer: JDWPWriter) {
        val value = this.value!!
        writer.append(
            ubyteArrayOf(
                (value shr 56).toUByte(),
                (value shr 48).toUByte(),
                (value shr 40).toUByte(),
                (value shr 32).toUByte(),
                (value shr 24).toUByte(),
                (value shr 16).toUByte(),
                (value shr 8).toUByte(),
                (value shr 0).toUByte(),
            )
        )
    }
}