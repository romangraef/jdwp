@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp

class ArrayBackedJDWPWriter(override val sizes: JDWPIDSizes, sizeHint: Int = 0, val loadFactor: Float = 0.75F) : JDWPWriter {
    var buffer = UByteArray(sizeHint)
    var size = 0

    companion object {
        val MIN_INCREASE = 16
    }

    fun ensureFreeCapacity(requiredCap: Int) {
        if (size + requiredCap < buffer.size) return
        buffer = buffer.copyOf(buffer.size + (buffer.size * loadFactor).toInt()
	        .coerceAtLeast(MIN_INCREASE)
	        .coerceAtLeast(requiredCap))
    }

    fun getResult(): UByteArray {
        return buffer.copyOf(size)
    }

    override fun append(byteArray: UByteArray, offset: Int, length: Int) {
        require(offset >= 0)
        require(length + offset <= byteArray.size)
        ensureFreeCapacity(length)
        byteArray.copyInto(buffer, size)
        size += byteArray.size
    }
}