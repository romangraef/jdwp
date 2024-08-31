package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Terminates the target VM with the given exit code. On some platforms, the exit code might be truncated, for example, to the low order 8 bits. All ids previously returned from the target VM become invalid. Threads running in the VM are abruptly terminated. A thread death exception is not thrown and finally blocks are not run.
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_Exit)
 */
class Exit : JDWPComposite(), JDWPCommandPayload<ExitReply> {
    /**
     * the exit code
     */
    var exitCode by useField(JDWPInt())
    override val reply = ExitReply()
    override val commandId: UByte get() = 10.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [Exit]
 */
class ExitReply : JDWPComposite(), JDWPReplyPayload {
}

