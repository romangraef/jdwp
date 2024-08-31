package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the sizes of variably-sized data types in the target VM.The returned values indicate the number of bytes used by the identifiers in command and reply packets.
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_IDSizes)
 */
class IDSizes : JDWPComposite(), JDWPCommandPayload<IDSizesReply> {
    override val reply = IDSizesReply()
    override val commandId: UByte get() = 7.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [IDSizes]
 */
class IDSizesReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * fieldID size in bytes
     */
    var fieldIDSize by useField(JDWPInt())
    /**
     * methodID size in bytes
     */
    var methodIDSize by useField(JDWPInt())
    /**
     * objectID size in bytes
     */
    var objectIDSize by useField(JDWPInt())
    /**
     * referenceTypeID size in bytes
     */
    var referenceTypeIDSize by useField(JDWPInt())
    /**
     * frameID size in bytes
     */
    var frameIDSize by useField(JDWPInt())
}





