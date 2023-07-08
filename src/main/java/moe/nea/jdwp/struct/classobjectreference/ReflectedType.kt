package moe.nea.jdwp.struct.classobjectreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the reference type reflected by this class object.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ClassObjectReference_ReflectedType)
 */
class ReflectedType : JDWPComposite(), JDWPCommandPayload<ReflectedTypeReply> {
    /**
     * The class object.
     */
    var classObject by useField(JDWPClassObjectId())
    override val reply = ReflectedTypeReply()
    override val commandId: UByte get() = 1.toUByte()
    override val commandSetId: UByte get() = 17.toUByte()
}
/**
 * Reply for [ReflectedType]
 */
class ReflectedTypeReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Kind of following reference type.
     */
    var refTypeTag by useField(JDWPByte())
    /**
     * reflected reference type
     */
    var typeID by useField(JDWPReferenceTypeId())
}



