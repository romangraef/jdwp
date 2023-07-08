package moe.nea.jdwp.struct.threadreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Suspends the thread. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadReference_Suspend)
 */
class Suspend : JDWPComposite(), JDWPCommandPayload<SuspendReply> {
    /**
     * The thread object ID.
     */
    var thread by useField(JDWPThreadId())
    override val reply = SuspendReply()
    override val commandId: UByte get() = 2.toUByte()
    override val commandSetId: UByte get() = 11.toUByte()
}
/**
 * Reply for [Suspend]
 */
class SuspendReply : JDWPComposite(), JDWPReplyPayload {
}

