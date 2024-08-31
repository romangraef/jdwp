package moe.nea.jdwp.struct.stringreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the characters contained in the string. 
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_StringReference_Value)
 */
class Value : JDWPComposite(), JDWPCommandPayload<ValueReply> {
    /**
     * The String object ID.
     */
    var stringObject by useField(JDWPObjectId())
    override val reply = ValueReply()
    override val commandId: UByte get() = 1.toUByte()
    override val commandSetId: UByte get() = 10.toUByte()
}
/**
 * Reply for [Value]
 */
class ValueReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * UTF-8 representation of the string value.
     */
    var stringValue by useField(JDWPString())
}


