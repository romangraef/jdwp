package moe.nea.jdwp.struct.threadreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the thread name. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadReference_Name)
 */
class Name : JDWPComposite(), JDWPCommandPayload<NameReply> {
    /**
     * The thread object ID.
     */
    var thread by useField(JDWPThreadId())
    override val reply = NameReply()
    override val commandId: UByte get() = 1.toUByte()
    override val commandSetId: UByte get() = 11.toUByte()
}
/**
 * Reply for [Name]
 */
class NameReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The thread name.
     */
    var threadName by useField(JDWPString())
}


