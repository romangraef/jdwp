@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp

import java.io.Closeable
import java.io.InputStream

class JDWPInputStreamReader(inputStream: InputStream) : JDWPReader, Closeable {
    private val backingStream = inputStream
    override val sizes: JDWPIDSizes = JDWPIDSizes()
    override fun close() {
        backingStream.close()
    }

    override fun consume(length: Int): UByteArray {
        val array = ByteArray(length)
        var offset = 0
        while (offset < length) {
            val read = backingStream.read(array, offset, length - offset)
            require(read > 0)
            offset += read
        }
        return array.toUByteArray()
    }
}