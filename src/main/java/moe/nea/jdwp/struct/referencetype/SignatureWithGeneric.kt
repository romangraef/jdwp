package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the JNI signature of a reference type along with the generic signature if there is one.  Generic signatures are described in the signature attribute section in . Since JDWP version 1.5.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ReferenceType_SignatureWithGeneric)
 */
class SignatureWithGeneric : JDWPComposite(), JDWPCommandPayload<SignatureWithGenericReply> {
    /**
     * The reference type ID.
     */
    var refType by useField(JDWPReferenceTypeId())
    override val reply = SignatureWithGenericReply()
    override val commandId: UByte get() = 13.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [SignatureWithGeneric]
 */
class SignatureWithGenericReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The JNI signature for the reference type.
     */
    var signature by useField(JDWPString())
    /**
     * The generic signature for the reference type or an empty string if there is none.
     */
    var genericSignature by useField(JDWPString())
}



