package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPComposite

class JDWPTaggedObjectId : JDWPComposite() {
    val tag by useField(JDWPEnum<JDWPTagConstants>())
    val objectId by useField(JDWPObjectId())
}