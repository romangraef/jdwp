package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPSingleContainer
import moe.nea.jdwp.JDWPWriter

class JDWPVector<T : Any>(
    private val size: JDWPSingleContainer<Int>,
    private val element: JDWPSingleContainer<T>
) : JDWPSingleContainer<List<T>> {
    override var value: List<T>? = null
    override fun read(reader: JDWPReader) {
        size.read(reader)
        val length = size.value!!
        (0 until length).map {
            element.read(reader)
            element.value!!
        }
    }

    override fun write(writer: JDWPWriter) {
        val list = value!!
        size.value = list.size
        size.write(writer)
        list.forEach {
            element.value = it
            element.write(writer)
        }
    }

}