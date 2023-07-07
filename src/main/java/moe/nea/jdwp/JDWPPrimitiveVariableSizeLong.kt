@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp

import kotlin.reflect.KProperty1

class JDWPPrimitiveVariableSizeLong(private val sizeProperty: (JDWPIDSizes) -> Int) : JDWPSingleState<Long>() {
    constructor(sizeProperty: KProperty1<JDWPIDSizes, Int>, value: Long) : this(sizeProperty) {
        this.value = value
    }

    override fun read(reader: JDWPReader) {
        val bytes = reader.consume(sizeProperty.invoke(reader.sizes))
        var result = 0L
        for (i in bytes.indices) {
            result = result or (bytes[i].toLong() shl (8 * (bytes.size - 1 - i)))
        }
        value = result
    }

    override fun write(writer: JDWPWriter) {
        val size = sizeProperty.invoke(writer.sizes)
        val value = this.value!!
        writer.append(UByteArray(size) { (value shr ((size - it - 1) * 8)).toUByte() })
    }
}