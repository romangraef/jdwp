package moe.nea.jdwp.struct.threadreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Force a method to return before it reaches a return statement.  
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadReference_ForceEarlyReturn)
 */
class ForceEarlyReturn : JDWPComposite(), JDWPCommandPayload<ForceEarlyReturnReply> {
    /**
     * The thread object ID.
     */
    var thread by useField(JDWPThreadId())
    /**
     * The value to return.
     */
    val value by useField(JDWPValue())
    override val reply = ForceEarlyReturnReply()
    override val commandId: UByte get() = 14.toUByte()
    override val commandSetId: UByte get() = 11.toUByte()
}
/**
 * Reply for [ForceEarlyReturn]
 */
class ForceEarlyReturnReply : JDWPComposite(), JDWPReplyPayload {
}


