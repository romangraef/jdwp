@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp

import java.io.Closeable
import java.io.OutputStream

class JDWPOutputStreamWriter(outputStream: OutputStream) : JDWPWriter, Closeable {
    override val sizes: JDWPIDSizes = JDWPIDSizes()
    private val backingStream = outputStream.buffered()
    override fun append(byteArray: UByteArray, offset: Int, length: Int) {
        backingStream.write(byteArray.toByteArray(), offset, length)
    }

    override fun close() {
        backingStream.close()
    }
}