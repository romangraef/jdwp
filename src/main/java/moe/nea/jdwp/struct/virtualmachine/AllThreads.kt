package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the live threads in the target VM. Threads that have not yet started or threads that have terminated are not included in the list.
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_AllThreads)
 */
class AllThreads : JDWPComposite(), JDWPCommandPayload<AllThreadsReply> {
    override val reply = AllThreadsReply()
    override val commandId: UByte get() = 4.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [AllThreads]
 */
class AllThreadsReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Number of threads that follow.
     */
    var threads by useField(JDWPInt())
    var threadsElements by useField(JDWPExternalVector(this::threads, ::AllThreadsReplyThreadsElement))
}


/**
 * Component for [AllThreadsReply]
 */
class AllThreadsReplyThreadsElement : JDWPComposite() {
    /**
     * A running thread
     */
    var thread by useField(JDWPThreadId())
}

