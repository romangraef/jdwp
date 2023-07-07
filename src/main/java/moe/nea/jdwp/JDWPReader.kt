@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp

interface JDWPReader {
    val sizes: JDWPIDSizes
    fun consume(length: Int): UByteArray

    /**
     * Acts like [consume] on `this`, but returns a new [JDWPReader] that delegates to `this` one,
     * except for the first [length] bytes, that instead get read from the returned array.
     * Modifications to the returned array, may be reflected in returned [JDWPReader].
     */
    fun peek(length: Int): Pair<JDWPReader, UByteArray> {
        val peeked = consume(length)
        return Pair(JDWPPeekReader(peeked, this), peeked)
    }
}

