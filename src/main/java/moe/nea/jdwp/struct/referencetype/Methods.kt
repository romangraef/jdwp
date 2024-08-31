package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns information for each method in a reference type. Inherited methods are not included. The list of methods will include constructors (identified with the name "<init>"), the initialization method (identified with the name "<clinit>") if present, and any synthetic methods created by the compiler. Methods are returned in the order they occur in the class file.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ReferenceType_Methods)
 */
class Methods : JDWPComposite(), JDWPCommandPayload<MethodsReply> {
    /**
     * The reference type ID.
     */
    var refType by useField(JDWPReferenceTypeId())
    override val reply = MethodsReply()
    override val commandId: UByte get() = 5.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [Methods]
 */
class MethodsReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Number of declared methods.
     */
    var declared by useField(JDWPInt())
    var declaredElements by useField(JDWPExternalVector(this::declared, ::MethodsReplyDeclaredElement))
}



/**
 * Component for [MethodsReply]
 */
class MethodsReplyDeclaredElement : JDWPComposite() {
    /**
     * Method ID.
     */
    var methodID by useField(JDWPMethodId())
    /**
     * Name of method.
     */
    var name by useField(JDWPString())
    /**
     * JNI signature of method.
     */
    var signature by useField(JDWPString())
    /**
     * The modifier bit flags (also known as access flags) which provide additional information on the  method declaration. Individual flag values are defined in Chapter 4 of The Java Virtual Machine Specification. In addition, The 0xf0000000 bit identifies the method as synthetic, if the synthetic attribute [capability](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_Capabilities) is available.
     */
    var modBits by useField(JDWPInt())
}




