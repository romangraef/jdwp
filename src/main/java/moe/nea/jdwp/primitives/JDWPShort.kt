@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPSingleState
import moe.nea.jdwp.JDWPWriter

class JDWPShort() : JDWPSingleState<Short>() {
    constructor(default: Short = 0) : this() {
        this.value = default
    }

    override fun read(reader: JDWPReader) {
        val bytes = reader.consume(2)
        value = ((bytes[0].toUInt() shl 8) or (bytes[1].toUInt())).toUShort().toShort()
    }

    override fun write(writer: JDWPWriter) {
        val value = this.value!!.toUInt()
        writer.append(
            ubyteArrayOf(
                (value shr 8).toUByte(),
                (value).toUByte()
            )
        )
    }
}
