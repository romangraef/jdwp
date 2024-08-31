package moe.nea.jdwp.struct.threadreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns monitor objects owned by the thread, along with stack depth at which the monitor was acquired. Returns stack depth of -1  if the implementation cannot determine the stack depth (e.g., for monitors acquired by JNI MonitorEnter).The thread must be suspended, and the returned information is relevant only while the thread is suspended. Requires canGetMonitorFrameInfo capability - see [CapabilitiesNew](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_CapabilitiesNew). 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadReference_OwnedMonitorsStackDepthInfo)
 */
class OwnedMonitorsStackDepthInfo : JDWPComposite(), JDWPCommandPayload<OwnedMonitorsStackDepthInfoReply> {
    /**
     * The thread object ID.
     */
    var thread by useField(JDWPThreadId())
    override val reply = OwnedMonitorsStackDepthInfoReply()
    override val commandId: UByte get() = 13.toUByte()
    override val commandSetId: UByte get() = 11.toUByte()
}
/**
 * Reply for [OwnedMonitorsStackDepthInfo]
 */
class OwnedMonitorsStackDepthInfoReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The number of owned monitors
     */
    var owned by useField(JDWPInt())
    var ownedElements by useField(JDWPExternalVector(this::owned, ::OwnedMonitorsStackDepthInfoReplyOwnedElement))
}



/**
 * Component for [OwnedMonitorsStackDepthInfoReply]
 */
class OwnedMonitorsStackDepthInfoReplyOwnedElement : JDWPComposite() {
    /**
     * An owned monitor
     */
    val monitor by useField(JDWPTaggedObjectId())
    /**
     * Stack depth location where monitor was acquired
     */
    var stack_depth by useField(JDWPInt())
}


