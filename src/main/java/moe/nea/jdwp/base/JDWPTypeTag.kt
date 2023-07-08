package moe.nea.jdwp.base

import moe.nea.jdwp.primitives.ByteTagged

enum class JDWPTypeTag(override val tag: UByte) : ByteTagged {
    CLASS(1.toUByte()),
    INTERFACE(2.toUByte()),
    ARRAY(3.toUByte()),
    ;
}
