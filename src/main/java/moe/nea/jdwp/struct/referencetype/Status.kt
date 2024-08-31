package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the current status of the reference type. The status indicates the extent to which the reference type has been initialized, as described in section 2.1.6 of . If the class is linked the PREPARED and VERIFIED bits in the returned status bits will be set. If the class is initialized the INITIALIZED bit in the returned status bits will be set. If an error occured during initialization then the ERROR bit in the returned status bits will be set. The returned status bits are undefined for array types and for primitive classes (such as java.lang.Integer.TYPE). 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ReferenceType_Status)
 */
class Status : JDWPComposite(), JDWPCommandPayload<StatusReply> {
    /**
     * The reference type ID.
     */
    var refType by useField(JDWPReferenceTypeId())
    override val reply = StatusReply()
    override val commandId: UByte get() = 9.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [Status]
 */
class StatusReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * [Status](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ClassStatus) bits:See [JDWP.ClassStatus](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ClassStatus)
     */
    var status by useField(JDWPInt())
}


