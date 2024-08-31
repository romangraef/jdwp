package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the type signature of a reference type. Type signature formats are the same as specified in [JVM TI GetClassSignature](../jvmti.html#GetClassSignature).
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ReferenceType_Signature)
 */
class Signature : JDWPComposite(), JDWPCommandPayload<SignatureReply> {
    /**
     * The reference type ID.
     */
    var refType by useField(JDWPReferenceTypeId())
    override val reply = SignatureReply()
    override val commandId: UByte get() = 1.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [Signature]
 */
class SignatureReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The JNI signature for the reference type.
     */
    var signature by useField(JDWPString())
}


