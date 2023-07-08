package moe.nea.jdwp.struct.arraytype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Creates a new array object of this type with a given length.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ArrayType_NewInstance)
 */
class NewInstance : JDWPComposite(), JDWPCommandPayload<NewInstanceReply> {
    /**
     * The array type of the new instance.
     */
    var arrType by useField(JDWPArrayTypeId())
    /**
     * The length of the array.
     */
    var length by useField(JDWPInt())
    override val reply = NewInstanceReply()
    override val commandId: UByte get() = 1.toUByte()
    override val commandSetId: UByte get() = 4.toUByte()
}
/**
 * Reply for [NewInstance]
 */
class NewInstanceReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The newly created array object.
     */
    val newArray by useField(JDWPTaggedObjectId())
}



