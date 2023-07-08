package moe.nea.jdwp.struct.threadreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the object, if any, for which this thread is waiting. The thread may be waiting to enter a monitor, or it may be waiting, via the java.lang.Object.wait method, for another thread to invoke the notify method. The thread must be suspended, and the returned information is relevant only while the thread is suspended. Requires canGetCurrentContendedMonitor capability - see .
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadReference_CurrentContendedMonitor)
 */
class CurrentContendedMonitor : JDWPComposite(), JDWPCommandPayload<CurrentContendedMonitorReply> {
    /**
     * The thread object ID.
     */
    var thread by useField(JDWPThreadId())
    override val reply = CurrentContendedMonitorReply()
    override val commandId: UByte get() = 9.toUByte()
    override val commandSetId: UByte get() = 11.toUByte()
}
/**
 * Reply for [CurrentContendedMonitor]
 */
class CurrentContendedMonitorReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The contended monitor, or null if there is no current contended monitor.
     */
    val monitor by useField(JDWPTaggedObjectId())
}


