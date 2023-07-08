package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the value of one or more static fields of the reference type. Each field must be member of the reference type or one of its superclasses, superinterfaces, or implemented interfaces. Access control is not enforced; for example, the values of private fields can be obtained.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ReferenceType_GetValues)
 */
class GetValues : JDWPComposite(), JDWPCommandPayload<GetValuesReply> {
    /**
     * The reference type ID.
     */
    var refType by useField(JDWPReferenceTypeId())
    /**
     * The number of values to get
     */
    var fields by useField(JDWPInt())
    var fieldsElements by useField(JDWPExternalVector(this::fields, ::GetValuesFieldsElement))
    override val reply = GetValuesReply()
    override val commandId: UByte get() = 6.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [GetValues]
 */
class GetValuesReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The number of values returned, always equal to fields, the number of values to get.
     */
    var values by useField(JDWPInt())
    var valuesElements by useField(JDWPExternalVector(this::values, ::GetValuesReplyValuesElement))
}



/**
 * Component for [GetValues]
 */
class GetValuesFieldsElement : JDWPComposite() {
    /**
     * A field to get
     */
    var fieldID by useField(JDWPFieldId())
}



/**
 * Component for [GetValuesReply]
 */
class GetValuesReplyValuesElement : JDWPComposite() {
    /**
     * The field value
     */
    val value by useField(JDWPValue())
}

