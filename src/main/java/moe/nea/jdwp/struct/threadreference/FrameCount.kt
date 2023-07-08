package moe.nea.jdwp.struct.threadreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the count of frames on this thread's stack. The thread must be suspended, and the returned count is valid only while the thread is suspended. Returns JDWP.Error.errorThreadNotSuspended if not suspended. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadReference_FrameCount)
 */
class FrameCount : JDWPComposite(), JDWPCommandPayload<FrameCountReply> {
    /**
     * The thread object ID.
     */
    var thread by useField(JDWPThreadId())
    override val reply = FrameCountReply()
    override val commandId: UByte get() = 7.toUByte()
    override val commandSetId: UByte get() = 11.toUByte()
}
/**
 * Reply for [FrameCount]
 */
class FrameCountReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The count of frames on this thread's stack.
     */
    var frameCount by useField(JDWPInt())
}


