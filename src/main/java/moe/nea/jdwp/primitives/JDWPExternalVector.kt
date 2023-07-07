package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPSingleContainer
import moe.nea.jdwp.JDWPWriter
import kotlin.reflect.KMutableProperty0

/**
 * Do not modify the size getter directly
 */
class JDWPExternalVector<T : Any>(
    private val sizeGetter: () -> Int, private val sizeSetter: (Int) -> Unit,
    private val element: JDWPSingleContainer<T>,
) : JDWPSingleContainer<List<T>> {
    constructor(prop: KMutableProperty0<Int>, element: JDWPSingleContainer<T>) :
            this(prop, prop::set, element)

    override var value: List<T>? = null
        set(value) {
            field = value
            sizeSetter(value!!.size)
        }

    override fun read(reader: JDWPReader) {
        val length = sizeGetter()
        (0 until length).map {
            element.read(reader)
            element.value!!
        }
    }

    override fun write(writer: JDWPWriter) {
        value!!.forEach {
            element.value = it
            element.write(writer)
        }
    }

    override fun toString(): String {
        return "JDWPExternalVector(${sizeGetter()}; $value)"
    }
}