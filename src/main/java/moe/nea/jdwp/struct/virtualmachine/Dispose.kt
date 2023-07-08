package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Invalidates this virtual machine mirror. The communication channel to the target VM is closed, and the target VM prepares to accept another subsequent connection from this debugger or another debugger, including the following tasks: Any current method invocations executing in the target VM are continued after the disconnection. Upon completion of any such method invocation, the invoking thread continues from the location where it was originally stopped. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_Dispose)
 */
class Dispose : JDWPComposite(), JDWPCommandPayload<DisposeReply> {
    override val reply = DisposeReply()
    override val commandId: UByte get() = 6.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [Dispose]
 */
class DisposeReply : JDWPComposite(), JDWPReplyPayload {
}
