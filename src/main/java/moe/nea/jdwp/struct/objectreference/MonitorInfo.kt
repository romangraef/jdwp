package moe.nea.jdwp.struct.objectreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns monitor information for an object. All threads int the VM must be suspended.Requires canGetMonitorInfo capability - see .
 */
class MonitorInfo : JDWPComposite(), JDWPCommandPayload<MonitorInfoReply> {
    /**
     * The object ID
     */
    var `object` by useField(JDWPObjectId())
    override val reply = MonitorInfoReply()
    override val commandId: UByte get() = 5.toUByte()
    override val commandSetId: UByte get() = 9.toUByte()
}
/**
 * Reply for [MonitorInfo]
 */
class MonitorInfoReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The monitor owner, or null if it is not currently owned.
     */
    var owner by useField(JDWPThreadId())
    /**
     * The number of times the monitor has been entered.
     */
    var entryCount by useField(JDWPInt())
    /**
     * The number of threads that are waiting for the monitor 0 if there is no current owner
     */
    var waiters by useField(JDWPInt())
    var waitersElements by useField(JDWPExternalVector(this::waiters, ::MonitorInfoWaitersElement))
}





class MonitorInfoWaitersElement : JDWPComposite() {
    /**
     * A thread waiting for this monitor.
     */
    var thread by useField(JDWPThreadId())
}

