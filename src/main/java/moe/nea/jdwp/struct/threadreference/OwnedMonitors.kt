package moe.nea.jdwp.struct.threadreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the objects whose monitors have been entered by this thread. The thread must be suspended, and the returned information is relevant only while the thread is suspended. Requires canGetOwnedMonitorInfo capability - see [CapabilitiesNew](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_CapabilitiesNew).
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadReference_OwnedMonitors)
 */
class OwnedMonitors : JDWPComposite(), JDWPCommandPayload<OwnedMonitorsReply> {
    /**
     * The thread object ID.
     */
    var thread by useField(JDWPThreadId())
    override val reply = OwnedMonitorsReply()
    override val commandId: UByte get() = 8.toUByte()
    override val commandSetId: UByte get() = 11.toUByte()
}
/**
 * Reply for [OwnedMonitors]
 */
class OwnedMonitorsReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The number of owned monitors
     */
    var owned by useField(JDWPInt())
    var ownedElements by useField(JDWPExternalVector(this::owned, ::OwnedMonitorsReplyOwnedElement))
}



/**
 * Component for [OwnedMonitorsReply]
 */
class OwnedMonitorsReplyOwnedElement : JDWPComposite() {
    /**
     * An owned monitor
     */
    val monitor by useField(JDWPTaggedObjectId())
}

