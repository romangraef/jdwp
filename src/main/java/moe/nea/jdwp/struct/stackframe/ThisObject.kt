package moe.nea.jdwp.struct.stackframe

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the value of the 'this' reference for this frame. If the frame's method is static or native, the reply will contain the null object reference.
 */
class ThisObject : JDWPComposite(), JDWPCommandPayload<ThisObjectReply> {
    /**
     * The frame's thread.
     */
    var thread by useField(JDWPThreadId())
    /**
     * The frame ID.
     */
    var frame by useField(JDWPFrameId())
    override val reply = ThisObjectReply()
    override val commandId: UByte get() = 3.toUByte()
    override val commandSetId: UByte get() = 16.toUByte()
}
/**
 * Reply for [ThisObject]
 */
class ThisObjectReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The 'this' object for this frame.
     */
    val objectThis by useField(JDWPTaggedObjectId())
}



