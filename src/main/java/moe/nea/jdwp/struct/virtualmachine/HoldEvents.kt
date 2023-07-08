package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Tells the target VM to stop sending events. Events are not discarded; they are held until a subsequent ReleaseEvents command is sent. This command is useful to control the number of events sent to the debugger VM in situations where very large numbers of events are generated. While events are held by the debugger back-end, application execution may be frozen by the debugger back-end to prevent buffer overflows on the back end.Responses to commands are never held and are not affected by thiscommand. If events are already being held, this command is ignored.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_HoldEvents)
 */
class HoldEvents : JDWPComposite(), JDWPCommandPayload<HoldEventsReply> {
    override val reply = HoldEventsReply()
    override val commandId: UByte get() = 15.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [HoldEvents]
 */
class HoldEventsReply : JDWPComposite(), JDWPReplyPayload {
}
