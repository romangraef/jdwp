package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPSingleContainer
import moe.nea.jdwp.JDWPWriter

abstract class JDWPVoid : JDWPSingleContainer<Unit> {
    override var value: Unit? = Unit
    override fun read(reader: JDWPReader) {
    }

    override fun write(writer: JDWPWriter) {
    }
}
