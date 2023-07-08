package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPElement
import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPSingleState
import moe.nea.jdwp.JDWPWriter

class JDWPConstructorContainer<T : JDWPElement>(val newinstance: () -> T) : JDWPSingleState<T>() {
    override fun read(reader: JDWPReader) {
        value = newinstance().also { it.read(reader) }
    }

    override fun write(writer: JDWPWriter) {
        value!!.write(writer)
    }
}