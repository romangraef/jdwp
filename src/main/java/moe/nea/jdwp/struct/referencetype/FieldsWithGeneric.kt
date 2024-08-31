package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns information, including the generic signature if any, for each field in a reference type. Inherited fields are not included. The field list will include any synthetic fields created by the compiler. Fields are returned in the order they occur in the class file.  Generic signatures are described in the signature attribute section in . Since JDWP version 1.5.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ReferenceType_FieldsWithGeneric)
 */
class FieldsWithGeneric : JDWPComposite(), JDWPCommandPayload<FieldsWithGenericReply> {
    /**
     * The reference type ID.
     */
    var refType by useField(JDWPReferenceTypeId())
    override val reply = FieldsWithGenericReply()
    override val commandId: UByte get() = 14.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [FieldsWithGeneric]
 */
class FieldsWithGenericReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Number of declared fields.
     */
    var declared by useField(JDWPInt())
    var declaredElements by useField(JDWPExternalVector(this::declared, ::FieldsWithGenericReplyDeclaredElement))
}



/**
 * Component for [FieldsWithGenericReply]
 */
class FieldsWithGenericReplyDeclaredElement : JDWPComposite() {
    /**
     * Field ID.
     */
    var fieldID by useField(JDWPFieldId())
    /**
     * The name of the field.
     */
    var name by useField(JDWPString())
    /**
     * The JNI signature of the field.
     */
    var signature by useField(JDWPString())
    /**
     * The generic signature of the field, or an empty string if there is none.
     */
    var genericSignature by useField(JDWPString())
    /**
     * The modifier bit flags (also known as access flags) which provide additional information on the  field declaration. Individual flag values are defined in Chapter 4 of The Java Virtual Machine Specification. In addition, The 0xf0000000 bit identifies the field as synthetic, if the synthetic attribute [capability](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_Capabilities) is available.
     */
    var modBits by useField(JDWPInt())
}





