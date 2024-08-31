package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Retrieve all of this VM's capabilities. The capabilities are returned as booleans, each indicating the presence or absence of a capability. The commands associated with each capability will return the NOT_IMPLEMENTED error if the cabability is not available.Since JDWP version 1.4.
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_CapabilitiesNew)
 */
class CapabilitiesNew : JDWPComposite(), JDWPCommandPayload<CapabilitiesNewReply> {
    override val reply = CapabilitiesNewReply()
    override val commandId: UByte get() = 17.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [CapabilitiesNew]
 */
class CapabilitiesNewReply : JDWPComposite(), JDWPReplyPayload {
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
    /**
     * Can the VM redefine classes?
     */
    var canRedefineClasses by useField(JDWPBoolean())
    /**
     * Can the VM add methods when redefining classes? @Deprecated(since="15") A JVM TI based JDWP back-end will never set this capability to true.
     */
    var canAddMethod by useField(JDWPBoolean())
    /**
     * Can the VM redefine classes in ways that are normally restricted?@Deprecated(since="15") A JVM TI based JDWP back-end will never set this capability to true.
     */
    var canUnrestrictedlyRedefineClasses by useField(JDWPBoolean())
    /**
     * Can the VM pop stack frames?
     */
    var canPopFrames by useField(JDWPBoolean())
    /**
     * Can the VM filter events by specific object?
     */
    var canUseInstanceFilters by useField(JDWPBoolean())
    /**
     * Can the VM get the source debug extension?
     */
    var canGetSourceDebugExtension by useField(JDWPBoolean())
    /**
     * Can the VM request VM death events?
     */
    var canRequestVMDeathEvent by useField(JDWPBoolean())
    /**
     * Can the VM set a default stratum?
     */
    var canSetDefaultStratum by useField(JDWPBoolean())
    /**
     * Can the VM return instances, counts of instances of classes and referring objects?
     */
    var canGetInstanceInfo by useField(JDWPBoolean())
    /**
     * Can the VM request monitor events?
     */
    var canRequestMonitorEvents by useField(JDWPBoolean())
    /**
     * Can the VM get monitors with frame depth info?
     */
    var canGetMonitorFrameInfo by useField(JDWPBoolean())
    /**
     * Can the VM filter class prepare events by source name?
     */
    var canUseSourceNameFilters by useField(JDWPBoolean())
    /**
     * Can the VM return the constant pool information?
     */
    var canGetConstantPool by useField(JDWPBoolean())
    /**
     * Can the VM force early return from a method?
     */
    var canForceEarlyReturn by useField(JDWPBoolean())
    /**
     * Reserved for future capability
     */
    var reserved22 by useField(JDWPBoolean())
    /**
     * Reserved for future capability
     */
    var reserved23 by useField(JDWPBoolean())
    /**
     * Reserved for future capability
     */
    var reserved24 by useField(JDWPBoolean())
    /**
     * Reserved for future capability
     */
    var reserved25 by useField(JDWPBoolean())
    /**
     * Reserved for future capability
     */
    var reserved26 by useField(JDWPBoolean())
    /**
     * Reserved for future capability
     */
    var reserved27 by useField(JDWPBoolean())
    /**
     * Reserved for future capability
     */
    var reserved28 by useField(JDWPBoolean())
    /**
     * Reserved for future capability
     */
    var reserved29 by useField(JDWPBoolean())
    /**
     * Reserved for future capability
     */
    var reserved30 by useField(JDWPBoolean())
    /**
     * Reserved for future capability
     */
    var reserved31 by useField(JDWPBoolean())
    /**
     * Reserved for future capability
     */
    var reserved32 by useField(JDWPBoolean())
}
































