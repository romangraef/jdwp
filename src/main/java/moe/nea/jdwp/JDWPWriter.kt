@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp

interface JDWPWriter {
    val sizes: JDWPIDSizes
    fun append(byteArray: UByteArray, offset: Int = 0, length: Int = byteArray.size)
}

