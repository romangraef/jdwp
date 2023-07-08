package moe.nea.jdwp.struct.eventrequest

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Removes all set breakpoints, a no-op if there are no breakpoints set.
 */
class ClearAllBreakpoints : JDWPComposite(), JDWPCommandPayload<ClearAllBreakpointsReply> {
    override val reply = ClearAllBreakpointsReply()
    override val commandId: UByte get() = 3.toUByte()
    override val commandSetId: UByte get() = 15.toUByte()
}
/**
 * Reply for [ClearAllBreakpoints]
 */
class ClearAllBreakpointsReply : JDWPComposite(), JDWPReplyPayload {
}
