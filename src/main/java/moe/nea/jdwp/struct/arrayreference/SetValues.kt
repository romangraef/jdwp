package moe.nea.jdwp.struct.arrayreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Sets a range of array components. The specified range must be within the bounds of the array. For primitive values, each value's type must match the array component type exactly. For object values, there must be a widening reference conversion from the value's type to thearray component type and the array component type must be loaded. 
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ArrayReference_SetValues)
 */
class SetValues : JDWPComposite(), JDWPCommandPayload<SetValuesReply> {
    /**
     * The array object ID.
     */
    var arrayObject by useField(JDWPArrayId())
    /**
     * The first index to set.
     */
    var firstIndex by useField(JDWPInt())
    /**
     * The number of values to set.
     */
    var values by useField(JDWPInt())
    var valuesElements by useField(JDWPExternalVector(this::values, ::SetValuesValuesElement))
    override val reply = SetValuesReply()
    override val commandId: UByte get() = 3.toUByte()
    override val commandSetId: UByte get() = 13.toUByte()
}
/**
 * Reply for [SetValues]
 */
class SetValuesReply : JDWPComposite(), JDWPReplyPayload {
}




/**
 * Component for [SetValues]
 */
class SetValuesValuesElement : JDWPComposite() {
    /**
     * A value to set.
     */
    var value by useField(JDWPUntaggedValue())
}

