package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Creates a new string object in the target VM and returns its id. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_CreateString)
 */
class CreateString : JDWPComposite(), JDWPCommandPayload<CreateStringReply> {
    /**
     * UTF-8 characters to use in the created string.
     */
    var utf by useField(JDWPString())
    override val reply = CreateStringReply()
    override val commandId: UByte get() = 11.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [CreateString]
 */
class CreateStringReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Created string (instance of java.lang.String)
     */
    var stringObject by useField(JDWPStringId())
}


