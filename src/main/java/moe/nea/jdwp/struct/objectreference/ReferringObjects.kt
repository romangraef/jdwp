package moe.nea.jdwp.struct.objectreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns objects that directly reference this object.  Only objects that are reachable for the purposes of garbage collection are returned. Note that an object can also be referenced in other ways, such as from a local variable in a stack frame, or from a JNI global reference.  Such non-object referrers are not returned by this command.
 */
class ReferringObjects : JDWPComposite(), JDWPCommandPayload<ReferringObjectsReply> {
    /**
     * The object ID
     */
    var `object` by useField(JDWPObjectId())
    /**
     * Maximum number of referring objects to return. Must be non-negative. If zero, all referring objects are returned.
     */
    var maxReferrers by useField(JDWPInt())
    override val reply = ReferringObjectsReply()
    override val commandId: UByte get() = 10.toUByte()
    override val commandSetId: UByte get() = 9.toUByte()
}
/**
 * Reply for [ReferringObjects]
 */
class ReferringObjectsReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The number of objects that follow.
     */
    var referringObjects by useField(JDWPInt())
    var referringObjectsElements by useField(JDWPExternalVector(this::referringObjects, ::ReferringObjectsReferringObjectsElement))
}




class ReferringObjectsReferringObjectsElement : JDWPComposite() {
    /**
     * An object that references this object.
     */
    val instance by useField(JDWPTaggedObjectId())
}

