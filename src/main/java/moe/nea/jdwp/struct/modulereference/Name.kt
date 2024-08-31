package moe.nea.jdwp.struct.modulereference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the name of this module.
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ModuleReference_Name)
 */
class Name : JDWPComposite(), JDWPCommandPayload<NameReply> {
    /**
     * This module.
     */
    var module by useField(JDWPModuleId())
    override val reply = NameReply()
    override val commandId: UByte get() = 1.toUByte()
    override val commandSetId: UByte get() = 18.toUByte()
}
/**
 * Reply for [Name]
 */
class NameReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The module's name.
     */
    var name by useField(JDWPString())
}


