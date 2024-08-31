package moe.nea.jdwp.struct.threadreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the current status of a thread. The thread status reply indicates the thread status the last time it was running. the suspend status provides information on the thread's suspension, if any.
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadReference_Status)
 */
class Status : JDWPComposite(), JDWPCommandPayload<StatusReply> {
    /**
     * The thread object ID.
     */
    var thread by useField(JDWPThreadId())
    override val reply = StatusReply()
    override val commandId: UByte get() = 4.toUByte()
    override val commandSetId: UByte get() = 11.toUByte()
}
/**
 * Reply for [Status]
 */
class StatusReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * One of the thread status codes See [JDWP.ThreadStatus](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadStatus)
     */
    var threadStatus by useField(JDWPInt())
    /**
     * One of the suspend status codes See [JDWP.SuspendStatus](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_SuspendStatus)
     */
    var suspendStatus by useField(JDWPInt())
}



