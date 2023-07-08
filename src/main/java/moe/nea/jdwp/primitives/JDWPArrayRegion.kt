package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPComposite
import moe.nea.jdwp.JDWPPrimitiveVariableSizeLong
import moe.nea.jdwp.base.JDWPTagConstants

class JDWPArrayRegion : JDWPComposite() {
    var typeTag by useField(JDWPEnum.ofByteTagged<JDWPTagConstants>())
    var elements by useField(
        JDWPVector(
            JDWPInt(),
            JDWPPrimitiveVariableSizeLong { typeTag.byteWidth ?: it.objectIdSize })
    )
}