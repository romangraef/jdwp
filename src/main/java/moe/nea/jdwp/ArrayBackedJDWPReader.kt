@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp

class ArrayBackedJDWPReader(
    val buffer: UByteArray,
    override val sizes: JDWPIDSizes,
    var offset: Int = 0,
) : JDWPReader {
    override fun consume(length: Int): UByteArray {
        require(length >= 0)
        val oldOffset = offset
        offset += length
        if (offset > buffer.size) {
            throw IncompletePacket()
        }
        return buffer.copyOfRange(oldOffset, offset)
    }

    override fun peek(length: Int): Pair<JDWPReader, UByteArray> {
        return Pair(
            ArrayBackedJDWPReader(buffer, sizes, offset),
            consume(length)
        )
    }
}