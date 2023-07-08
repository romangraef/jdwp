package moe.nea.jdwp.struct.threadreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the current call stack of a suspended thread. The sequence of frames starts with the currently executing frame, followed by its caller, and so on. The thread must be suspended, and the returned frameID is valid only while the thread is suspended. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ThreadReference_Frames)
 */
class Frames : JDWPComposite(), JDWPCommandPayload<FramesReply> {
    /**
     * The thread object ID.
     */
    var thread by useField(JDWPThreadId())
    /**
     * The index of the first frame to retrieve.
     */
    var startFrame by useField(JDWPInt())
    /**
     * The count of frames to retrieve (-1 means all remaining).
     */
    var length by useField(JDWPInt())
    override val reply = FramesReply()
    override val commandId: UByte get() = 6.toUByte()
    override val commandSetId: UByte get() = 11.toUByte()
}
/**
 * Reply for [Frames]
 */
class FramesReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The number of frames retreived
     */
    var frames by useField(JDWPInt())
    var framesElements by useField(JDWPExternalVector(this::frames, ::FramesReplyFramesElement))
}





/**
 * Component for [FramesReply]
 */
class FramesReplyFramesElement : JDWPComposite() {
    /**
     * The ID of this frame.
     */
    var frameID by useField(JDWPFrameId())
    /**
     * The current location of this frame
     */
    val location by useField(JDWPLocation())
}


