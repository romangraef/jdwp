package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Tells the target VM to continue sending events. This command is used to restore normal activity after a HoldEvents command. If there is no current HoldEvents command in effect, this command is ignored.
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_ReleaseEvents)
 */
class ReleaseEvents : JDWPComposite(), JDWPCommandPayload<ReleaseEventsReply> {
    override val reply = ReleaseEventsReply()
    override val commandId: UByte get() = 16.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [ReleaseEvents]
 */
class ReleaseEventsReply : JDWPComposite(), JDWPReplyPayload {
}
