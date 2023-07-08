package moe.nea.jdwp.struct.arrayreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns a range of array components. The specified range must be within the bounds of the array. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ArrayReference_GetValues)
 */
class GetValues : JDWPComposite(), JDWPCommandPayload<GetValuesReply> {
    /**
     * The array object ID.
     */
    var arrayObject by useField(JDWPArrayId())
    /**
     * The first index to retrieve.
     */
    var firstIndex by useField(JDWPInt())
    /**
     * The number of components to retrieve.
     */
    var length by useField(JDWPInt())
    override val reply = GetValuesReply()
    override val commandId: UByte get() = 2.toUByte()
    override val commandSetId: UByte get() = 13.toUByte()
}
/**
 * Reply for [GetValues]
 */
class GetValuesReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The retrieved values. If the values are objects, they are tagged-values; otherwise, they are untagged-values
     */
    val values by useField(JDWPArrayRegion())
}




