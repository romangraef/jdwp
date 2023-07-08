package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Retrieve this VM's capabilities. The capabilities are returned as booleans, each indicating the presence or absence of a capability. The commands associated with each capability will return the NOT_IMPLEMENTED error if the cabability is not available.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_Capabilities)
 */
class Capabilities : JDWPComposite(), JDWPCommandPayload<CapabilitiesReply> {
    override val reply = CapabilitiesReply()
    override val commandId: UByte get() = 12.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [Capabilities]
 */
class CapabilitiesReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Can the VM watch field modification, and therefore can it send the Modification Watchpoint Event?
     */
    var canWatchFieldModification by useField(JDWPBoolean())
    /**
     * Can the VM watch field access, and therefore can it send the Access Watchpoint Event?
     */
    var canWatchFieldAccess by useField(JDWPBoolean())
    /**
     * Can the VM get the bytecodes of a given method?
     */
    var canGetBytecodes by useField(JDWPBoolean())
    /**
     * Can the VM determine whether a field or method is synthetic? (that is, can the VM determine if the method or the field was invented by the compiler?)
     */
    var canGetSyntheticAttribute by useField(JDWPBoolean())
    /**
     * Can the VM get the owned monitors infornation for a thread?
     */
    var canGetOwnedMonitorInfo by useField(JDWPBoolean())
    /**
     * Can the VM get the current contended monitor of a thread?
     */
    var canGetCurrentContendedMonitor by useField(JDWPBoolean())
    /**
     * Can the VM get the monitor information for a given object?
     */
    var canGetMonitorInfo by useField(JDWPBoolean())
}







