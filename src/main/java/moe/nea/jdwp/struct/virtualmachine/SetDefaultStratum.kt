package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Set the default stratum. Requires canSetDefaultStratum capability - see .
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

