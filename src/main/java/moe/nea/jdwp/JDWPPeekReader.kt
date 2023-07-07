@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp

internal class JDWPPeekReader(private val array: UByteArray, private val delegate: JDWPReader) : JDWPReader {
    private var offset = 0
    override val sizes: JDWPIDSizes
        get() = delegate.sizes

    override fun consume(length: Int): UByteArray {
        require(length >= 0)
        if (offset >= array.size)
            return delegate.consume(length)
        val fromPeeked = array.copyOfRange(offset, (offset + length).coerceAtMost(array.size))
        offset += length
        if (fromPeeked.size < length) {
            return fromPeeked + delegate.consume(fromPeeked.size - length)
        }
        return fromPeeked
    }
}
