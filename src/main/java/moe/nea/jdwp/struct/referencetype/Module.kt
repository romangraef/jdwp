package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the module that this reference type belongs to.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ReferenceType_Module)
 */
class Module : JDWPComposite(), JDWPCommandPayload<ModuleReply> {
    /**
     * The reference type.
     */
    var refType by useField(JDWPReferenceTypeId())
    override val reply = ModuleReply()
    override val commandId: UByte get() = 19.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [Module]
 */
class ModuleReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The module this reference type belongs to.
     */
    var module by useField(JDWPModuleId())
}


