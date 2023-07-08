package moe.nea.jdwp.struct.threadreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the thread group that contains a given thread.
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


