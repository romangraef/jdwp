package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the modifiers (also known as access flags) for a reference type. The returned bit mask contains information on the declaration of the reference type. If the reference type is an array or a primitive class (for example, java.lang.Integer.TYPE), the value of the returned bit mask is undefined.
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ReferenceType_Modifiers)
 */
class Modifiers : JDWPComposite(), JDWPCommandPayload<ModifiersReply> {
    /**
     * The reference type ID.
     */
    var refType by useField(JDWPReferenceTypeId())
    override val reply = ModifiersReply()
    override val commandId: UByte get() = 3.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [Modifiers]
 */
class ModifiersReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Modifier bits as defined in Chapter 4 of The Java Virtual Machine Specification
     */
    var modBits by useField(JDWPInt())
}


