package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Resumes execution of the application after the suspend command or an event has stopped it. Suspensions of the Virtual Machine and individual threads are counted. If a particular thread is suspended n times, it must resumed n times before it will continue.
 */
class Resume : JDWPComposite(), JDWPCommandPayload<ResumeReply> {
    override val reply = ResumeReply()
    override val commandId: UByte get() = 9.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [Resume]
 */
class ResumeReply : JDWPComposite(), JDWPReplyPayload {
}
