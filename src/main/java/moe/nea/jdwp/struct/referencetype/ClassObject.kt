package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the class object corresponding to this type. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ReferenceType_ClassObject)
 */
class ClassObject : JDWPComposite(), JDWPCommandPayload<ClassObjectReply> {
    /**
     * The reference type ID.
     */
    var refType by useField(JDWPReferenceTypeId())
    override val reply = ClassObjectReply()
    override val commandId: UByte get() = 11.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [ClassObject]
 */
class ClassObjectReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * class object.
     */
    var classObject by useField(JDWPClassObjectId())
}


