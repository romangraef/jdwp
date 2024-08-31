package moe.nea.jdwp.struct.threadgroupreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the live platform threads and the thread groups directly contained in this thread group. Virtual threads are not included. Threads and thread groups in child thread groups are not included. A thread is alive if it has been started and has not yet terminated.
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadGroupReference_Children)
 */
class Children : JDWPComposite(), JDWPCommandPayload<ChildrenReply> {
    /**
     * The thread group object ID.
     */
    var group by useField(JDWPThreadGroupId())
    override val reply = ChildrenReply()
    override val commandId: UByte get() = 3.toUByte()
    override val commandSetId: UByte get() = 12.toUByte()
}
/**
 * Reply for [Children]
 */
class ChildrenReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The number of live child threads.
     */
    var childThreads by useField(JDWPInt())
    var childThreadsElements by useField(JDWPExternalVector(this::childThreads, ::ChildrenReplyChildThreadsElement))
    /**
     * The number of active child thread groups.
     */
    var childGroups by useField(JDWPInt())
    var childGroupsElements by useField(JDWPExternalVector(this::childGroups, ::ChildrenReplyChildGroupsElement))
}



/**
 * Component for [ChildrenReply]
 */
class ChildrenReplyChildThreadsElement : JDWPComposite() {
    /**
     * A direct child thread ID.
     */
    var childThread by useField(JDWPThreadId())
}



/**
 * Component for [ChildrenReply]
 */
class ChildrenReplyChildGroupsElement : JDWPComposite() {
    /**
     * A direct child thread group ID.
     */
    var childGroup by useField(JDWPThreadGroupId())
}

