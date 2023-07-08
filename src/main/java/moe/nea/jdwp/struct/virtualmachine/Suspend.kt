package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Suspends the execution of the application running in the target VM. All Java threads currently running will be suspended. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_Suspend)
 */
class Suspend : JDWPComposite(), JDWPCommandPayload<SuspendReply> {
    override val reply = SuspendReply()
    override val commandId: UByte get() = 8.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [Suspend]
 */
class SuspendReply : JDWPComposite(), JDWPReplyPayload {
}
