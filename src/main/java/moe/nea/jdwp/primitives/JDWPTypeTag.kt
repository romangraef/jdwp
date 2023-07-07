package moe.nea.jdwp.primitives

enum class JDWPTypeTag(override val byteTag: UByte) : ByteTagged {
    CLASS(1.toUByte()),
    INTERFACE(2.toUByte()),
    ARRAY(3.toUByte()),
    ;
}
