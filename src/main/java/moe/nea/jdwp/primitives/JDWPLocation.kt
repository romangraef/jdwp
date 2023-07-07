package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPComposite

class JDWPLocation : JDWPComposite() {
    val locationClassKind by useField(JDWPEnum<JDWPTypeTag>())
    val locationClass by useField(JDWPClassId())
    val locationMethod by useField(JDWPMethodId())
    val locationIndex by useField(JDWPByte())
}
