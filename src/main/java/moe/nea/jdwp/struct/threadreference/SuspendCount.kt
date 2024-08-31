package moe.nea.jdwp.struct.threadreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Get the suspend count for this thread. The suspend count is the  number of times the thread has been suspended through the thread-level or VM-level suspend commands without a corresponding resume 
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadReference_SuspendCount)
 */
class SuspendCount : JDWPComposite(), JDWPCommandPayload<SuspendCountReply> {
    /**
     * The thread object ID.
     */
    var thread by useField(JDWPThreadId())
    override val reply = SuspendCountReply()
    override val commandId: UByte get() = 12.toUByte()
    override val commandSetId: UByte get() = 11.toUByte()
}
/**
 * Reply for [SuspendCount]
 */
class SuspendCountReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The number of outstanding suspends of this thread.
     */
    var suspendCount by useField(JDWPInt())
}


