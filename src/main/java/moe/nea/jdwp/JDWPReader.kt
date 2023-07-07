@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp

interface JDWPReader {
    val sizes: JDWPIDSizes
    fun consume(length: Int): UByteArray
}

