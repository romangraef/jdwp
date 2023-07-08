package moe.nea.jdwp.struct.arrayreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the number of components in a given array. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ArrayReference_Length)
 */
class Length : JDWPComposite(), JDWPCommandPayload<LengthReply> {
    /**
     * The array object ID.
     */
    var arrayObject by useField(JDWPArrayId())
    override val reply = LengthReply()
    override val commandId: UByte get() = 1.toUByte()
    override val commandSetId: UByte get() = 13.toUByte()
}
/**
 * Reply for [Length]
 */
class LengthReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The length of the array.
     */
    var arrayLength by useField(JDWPInt())
}


