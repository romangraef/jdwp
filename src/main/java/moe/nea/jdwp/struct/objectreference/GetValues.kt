package moe.nea.jdwp.struct.objectreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the value of one or more instance fields. Each field must be member of the object's type or one of its superclasses, superinterfaces, or implemented interfaces. Access control is not enforced; for example, the values of private fields can be obtained.
 */
class GetValues : JDWPComposite(), JDWPCommandPayload<GetValuesReply> {
    /**
     * The object ID
     */
    var `object` by useField(JDWPObjectId())
    /**
     * The number of values to get
     */
    var fields by useField(JDWPInt())
    var fieldsElements by useField(JDWPExternalVector(this::fields, ::GetValuesFieldsElement))
    override val reply = GetValuesReply()
    override val commandId: UByte get() = 2.toUByte()
    override val commandSetId: UByte get() = 9.toUByte()
}
/**
 * Reply for [GetValues]
 */
class GetValuesReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The number of values returned, always equal to 'fields', the number of values to get. Field values are ordered in the reply in the same order as corresponding fieldIDs in the command.
     */
    var values by useField(JDWPInt())
    var valuesElements by useField(JDWPExternalVector(this::values, ::GetValuesValuesElement))
}



class GetValuesFieldsElement : JDWPComposite() {
    /**
     * Field to get.
     */
    var fieldID by useField(JDWPFieldId())
}



class GetValuesValuesElement : JDWPComposite() {
    /**
     * The field value
     */
    val value by useField(JDWPValue())
}

