package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPComposite
import moe.nea.jdwp.struct.base.JDWPTagConstants

class JDWPTaggedObjectId : JDWPComposite() {
    var tag by useField(JDWPEnum.ofByteTagged<JDWPTagConstants>())
    var objectId by useField(JDWPObjectId())
}