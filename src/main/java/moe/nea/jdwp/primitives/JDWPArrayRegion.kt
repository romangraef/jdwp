package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPComposite
import moe.nea.jdwp.JDWPPrimitiveVariableSizeLong

class JDWPArrayRegion : JDWPComposite() {
    val typeTag by useField(JDWPEnum<JDWPTagConstants>())
    val elements by useField(
        JDWPVector(
            JDWPInt(),
            JDWPPrimitiveVariableSizeLong { typeTag.byteWidth ?: it.objectIdSize })
    )
}