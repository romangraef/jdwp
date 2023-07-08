package moe.nea.jdwp.struct.stringreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the characters contained in the string.
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


