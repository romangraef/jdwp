package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns information, including the generic signature if any, for each method in a reference type. Inherited methodss are not included. The list of methods will include constructors (identified with the name "<init>"), the initialization method (identified with the name "<clinit>") if present, and any synthetic methods created by the compiler. Methods are returned in the order they occur in the class file.  Generic signatures are described in the signature attribute section in . Since JDWP version 1.5.
 */
class MethodsWithGeneric : JDWPComposite(), JDWPCommandPayload<MethodsWithGenericReply> {
    /**
     * The reference type ID.
     */
    var refType by useField(JDWPReferenceTypeId())
    override val reply = MethodsWithGenericReply()
    override val commandId: UByte get() = 15.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [MethodsWithGeneric]
 */
class MethodsWithGenericReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Number of declared methods.
     */
    var declared by useField(JDWPInt())
    var declaredElements by useField(JDWPExternalVector(this::declared, ::MethodsWithGenericDeclaredElement))
}



class MethodsWithGenericDeclaredElement : JDWPComposite() {
    /**
     * Method ID.
     */
    var methodID by useField(JDWPMethodId())
    /**
     * The name of the method.
     */
    var name by useField(JDWPString())
    /**
     * The JNI signature of the method.
     */
    var signature by useField(JDWPString())
    /**
     * The generic signature of the method, or an empty string if there is none.
     */
    var genericSignature by useField(JDWPString())
    /**
     * The modifier bit flags (also known as access flags) which provide additional information on the  method declaration. Individual flag values are defined in Chapter 4 of The Java Virtual Machine Specification. In addition, The 0xf0000000 bit identifies the method as synthetic, if the synthetic attribute capability is available.
     */
    var modBits by useField(JDWPInt())
}





