package moe.nea.jdwp.struct.threadgroupreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the thread group, if any, which contains a given thread group. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadGroupReference_Parent)
 */
class Parent : JDWPComposite(), JDWPCommandPayload<ParentReply> {
    /**
     * The thread group object ID.
     */
    var group by useField(JDWPThreadGroupId())
    override val reply = ParentReply()
    override val commandId: UByte get() = 2.toUByte()
    override val commandSetId: UByte get() = 12.toUByte()
}
/**
 * Reply for [Parent]
 */
class ParentReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The parent thread group object, or null if the given thread group is a top-level thread group
     */
    var parentGroup by useField(JDWPThreadGroupId())
}


