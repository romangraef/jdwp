package moe.nea.jdwp.struct.threadreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Interrupt the thread, as if done by java.lang.Thread.interrupt 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadReference_Interrupt)
 */
class Interrupt : JDWPComposite(), JDWPCommandPayload<InterruptReply> {
    /**
     * The thread object ID.
     */
    var thread by useField(JDWPThreadId())
    override val reply = InterruptReply()
    override val commandId: UByte get() = 11.toUByte()
    override val commandSetId: UByte get() = 11.toUByte()
}
/**
 * Reply for [Interrupt]
 */
class InterruptReply : JDWPComposite(), JDWPReplyPayload {
}

