package moe.nea.jdwp.struct.threadgroupreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the thread group name. 
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadGroupReference_Name)
 */
class Name : JDWPComposite(), JDWPCommandPayload<NameReply> {
    /**
     * The thread group object ID.
     */
    var group by useField(JDWPThreadGroupId())
    override val reply = NameReply()
    override val commandId: UByte get() = 1.toUByte()
    override val commandSetId: UByte get() = 12.toUByte()
}
/**
 * Reply for [Name]
 */
class NameReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The thread group's name.
     */
    var groupName by useField(JDWPString())
}


