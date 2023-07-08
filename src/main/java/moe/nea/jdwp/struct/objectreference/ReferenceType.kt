package moe.nea.jdwp.struct.objectreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the runtime type of the object. The runtime type will be a class or an array.
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
     * Kind of following reference type.
     */
    var refTypeTag by useField(JDWPByte())
    /**
     * The runtime reference type.
     */
    var typeID by useField(JDWPReferenceTypeId())
}



