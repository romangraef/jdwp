package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPComposite
import moe.nea.jdwp.struct.base.JDWPTypeTag

class JDWPLocation : JDWPComposite() {
    var locationClassKind by useField(JDWPEnum.ofByteTagged<JDWPTypeTag>())
    var locationClass by useField(JDWPClassId())
    var locationMethod by useField(JDWPMethodId())
    var locationIndex by useField(JDWPByte())
}
