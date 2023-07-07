package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPComposite

class JDWPLocation : JDWPComposite() {
    var locationClassKind by useField(JDWPEnum<JDWPTypeTag>())
    var locationClass by useField(JDWPClassId())
    var locationMethod by useField(JDWPMethodId())
    var locationIndex by useField(JDWPByte())
}
