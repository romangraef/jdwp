package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPComposite

class JDWPTaggedObjectId : JDWPComposite() {
    var tag by useField(JDWPEnum<JDWPTagConstants>())
    var objectId by useField(JDWPObjectId())
}