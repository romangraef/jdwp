package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Set the default stratum. Requires canSetDefaultStratum capability - see [CapabilitiesNew](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_CapabilitiesNew).
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_SetDefaultStratum)
 */
class SetDefaultStratum : JDWPComposite(), JDWPCommandPayload<SetDefaultStratumReply> {
    /**
     * default stratum, or empty string to use reference type default.
     */
    var stratumID by useField(JDWPString())
    override val reply = SetDefaultStratumReply()
    override val commandId: UByte get() = 19.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [SetDefaultStratum]
 */
class SetDefaultStratumReply : JDWPComposite(), JDWPReplyPayload {
}

