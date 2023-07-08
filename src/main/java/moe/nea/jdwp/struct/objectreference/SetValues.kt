package moe.nea.jdwp.struct.objectreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Sets the value of one or more instance fields. Each field must be member of the object's type or one of its superclasses, superinterfaces, or implemented interfaces. Access control is not enforced; for example, the values of private fields can be set. For primitive values, the value's type must match the field's type exactly. For object values, there must be a widening reference conversion from the value's type to thefield's type and the field's type must be loaded.
 */
class SetValues : JDWPComposite(), JDWPCommandPayload<SetValuesReply> {
    /**
     * The object ID
     */
    var `object` by useField(JDWPObjectId())
    /**
     * The number of fields to set.
     */
    var values by useField(JDWPInt())
    var valuesElements by useField(JDWPExternalVector(this::values, ::SetValuesValuesElement))
    override val reply = SetValuesReply()
    override val commandId: UByte get() = 3.toUByte()
    override val commandSetId: UByte get() = 9.toUByte()
}
/**
 * Reply for [SetValues]
 */
class SetValuesReply : JDWPComposite(), JDWPReplyPayload {
}



class SetValuesValuesElement : JDWPComposite() {
    /**
     * Field to set.
     */
    var fieldID by useField(JDWPFieldId())
    /**
     * Value to put in the field.
     */
    var value by useField(JDWPUntaggedValue())
}


