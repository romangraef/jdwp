package moe.nea.jdwp.struct.threadreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Resumes the execution of a given thread. If this thread was not previously suspended by the front-end, calling this command has no effect. Otherwise, the count of pending suspends on this thread is decremented. If it is decremented to 0, the thread will continue to execute. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadReference_Resume)
 */
class Resume : JDWPComposite(), JDWPCommandPayload<ResumeReply> {
    /**
     * The thread object ID.
     */
    var thread by useField(JDWPThreadId())
    override val reply = ResumeReply()
    override val commandId: UByte get() = 3.toUByte()
    override val commandSetId: UByte get() = 11.toUByte()
}
/**
 * Reply for [Resume]
 */
class ResumeReply : JDWPComposite(), JDWPReplyPayload {
}

