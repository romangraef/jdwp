package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns reference types for all classes currently loaded by the target VM.  Both the JNI signature and the generic signature are returned for each class.  Generic signatures are described in the signature attribute section in . Since JDWP version 1.5.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_AllClassesWithGeneric)
 */
class AllClassesWithGeneric : JDWPComposite(), JDWPCommandPayload<AllClassesWithGenericReply> {
    override val reply = AllClassesWithGenericReply()
    override val commandId: UByte get() = 20.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [AllClassesWithGeneric]
 */
class AllClassesWithGenericReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Number of reference types that follow.
     */
    var classes by useField(JDWPInt())
    var classesElements by useField(JDWPExternalVector(this::classes, ::AllClassesWithGenericReplyClassesElement))
}


/**
 * Component for [AllClassesWithGenericReply]
 */
class AllClassesWithGenericReplyClassesElement : JDWPComposite() {
    /**
     * [Kind](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_TypeTag) of following reference type.
     */
    var refTypeTag by useField(JDWPByte())
    /**
     * Loaded reference type
     */
    var typeID by useField(JDWPReferenceTypeId())
    /**
     * The JNI signature of the loaded reference type.
     */
    var signature by useField(JDWPString())
    /**
     * The generic signature of the loaded reference type or an empty string if there is none.
     */
    var genericSignature by useField(JDWPString())
    /**
     * The current class [status.](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ClassStatus)
     */
    var status by useField(JDWPInt())
}





