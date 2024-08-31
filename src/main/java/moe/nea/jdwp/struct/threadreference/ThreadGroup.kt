package moe.nea.jdwp.struct.threadreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the thread group that contains a given thread. 
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadReference_ThreadGroup)
 */
class ThreadGroup : JDWPComposite(), JDWPCommandPayload<ThreadGroupReply> {
    /**
     * The thread object ID.
     */
    var thread by useField(JDWPThreadId())
    override val reply = ThreadGroupReply()
    override val commandId: UByte get() = 5.toUByte()
    override val commandSetId: UByte get() = 11.toUByte()
}
/**
 * Reply for [ThreadGroup]
 */
class ThreadGroupReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The thread group of this thread.
     */
    var group by useField(JDWPThreadGroupId())
}


