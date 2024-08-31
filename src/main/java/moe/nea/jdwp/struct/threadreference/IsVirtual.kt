package moe.nea.jdwp.struct.threadreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Determine if a thread is a [virtual thread](../../api/java.base/java/lang/Thread.html#virtual-threads).
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadReference_IsVirtual)
 */
class IsVirtual : JDWPComposite(), JDWPCommandPayload<IsVirtualReply> {
    /**
     * The thread object ID.
     */
    var thread by useField(JDWPThreadId())
    override val reply = IsVirtualReply()
    override val commandId: UByte get() = 15.toUByte()
    override val commandSetId: UByte get() = 11.toUByte()
}
/**
 * Reply for [IsVirtual]
 */
class IsVirtualReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * true if the thread is a virtual thread.
     */
    var isVirtual by useField(JDWPBoolean())
}


