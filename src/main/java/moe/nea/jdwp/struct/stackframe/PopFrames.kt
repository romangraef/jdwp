package moe.nea.jdwp.struct.stackframe

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Pop the top-most stack frames of the thread stack, up to, and including 'frame'. The thread must be suspended to perform this command. The top-most stack frames are discarded and the stack frame previous to 'frame' becomes the current frame. The operand stack is restored -- the argument values are added back and if the invoke was not ,  is added back as well. The Java virtual machine program counter is restored to the opcode of the invoke instruction.
 */
class PopFrames : JDWPComposite(), JDWPCommandPayload<PopFramesReply> {
    /**
     * The thread object ID.
     */
    var thread by useField(JDWPThreadId())
    /**
     * The frame ID.
     */
    var frame by useField(JDWPFrameId())
    override val reply = PopFramesReply()
    override val commandId: UByte get() = 4.toUByte()
    override val commandSetId: UByte get() = 16.toUByte()
}
/**
 * Reply for [PopFrames]
 */
class PopFramesReply : JDWPComposite(), JDWPReplyPayload {
}


