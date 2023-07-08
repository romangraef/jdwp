@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPSingleContainer
import moe.nea.jdwp.JDWPWriter
import java.util.*
import kotlin.reflect.KMutableProperty0

class JDWPByteArray(val sizeGetter: () -> Int, val sizeSetter: (Int) -> Unit) : JDWPSingleContainer<UByteArray> {
    constructor(property: KMutableProperty0<Int?>) : this({ property.get()!! }, property::set)
    constructor(jdwpInt: JDWPInt) : this(jdwpInt::value)


    override var value: UByteArray? = null
        set(value) {
            field = value
            sizeSetter(value!!.size)
        }

    override fun read(reader: JDWPReader) {
        value = reader.consume(sizeGetter())
    }

    override fun write(writer: JDWPWriter) {
        writer.append(value!!)
    }

    override fun toString(): String {
        return "JDWPByteArray(${Arrays.toString(value?.toByteArray())})"
    }
}