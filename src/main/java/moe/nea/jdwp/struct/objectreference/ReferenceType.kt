package moe.nea.jdwp.struct.objectreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the runtime type of the object. The runtime type will be a class or an array. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ObjectReference_ReferenceType)
 */
class ReferenceType : JDWPComposite(), JDWPCommandPayload<ReferenceTypeReply> {
    /**
     * The object ID
     */
    var `object` by useField(JDWPObjectId())
    override val reply = ReferenceTypeReply()
    override val commandId: UByte get() = 1.toUByte()
    override val commandSetId: UByte get() = 9.toUByte()
}
/**
 * Reply for [ReferenceType]
 */
class ReferenceTypeReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * [Kind](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_TypeTag) of following reference type.
     */
    var refTypeTag by useField(JDWPByte())
    /**
     * The runtime reference type.
     */
    var typeID by useField(JDWPReferenceTypeId())
}



