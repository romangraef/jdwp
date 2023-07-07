package moe.nea.jdwp

interface JDWPElement {
    fun read(reader: JDWPReader)
    fun write(writer: JDWPWriter)
}