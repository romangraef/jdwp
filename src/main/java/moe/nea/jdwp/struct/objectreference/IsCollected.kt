package moe.nea.jdwp.struct.objectreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Determines whether an object has been garbage collected in the target VM.
 */
class IsCollected : JDWPComposite(), JDWPCommandPayload<IsCollectedReply> {
    /**
     * The object ID
     */
    var `object` by useField(JDWPObjectId())
    override val reply = IsCollectedReply()
    override val commandId: UByte get() = 9.toUByte()
    override val commandSetId: UByte get() = 9.toUByte()
}
/**
 * Reply for [IsCollected]
 */
class IsCollectedReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * true if the object has been collected; false otherwise
     */
    var isCollected by useField(JDWPBoolean())
}


