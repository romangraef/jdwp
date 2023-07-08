package moe.nea.jdwp.struct.method

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Retrieve the method's bytecodes as defined in . Requires canGetBytecodes capability - see .
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_Method_Bytecodes)
 */
class Bytecodes : JDWPComposite(), JDWPCommandPayload<BytecodesReply> {
    /**
     * The class.
     */
    var refType by useField(JDWPReferenceTypeId())
    /**
     * The method.
     */
    var methodID by useField(JDWPMethodId())
    override val reply = BytecodesReply()
    override val commandId: UByte get() = 3.toUByte()
    override val commandSetId: UByte get() = 6.toUByte()
}
/**
 * Reply for [Bytecodes]
 */
class BytecodesReply : JDWPComposite(), JDWPReplyPayload {
    var bytes by useField(JDWPInt())
    var bytesElements by useField(JDWPExternalVector(this::bytes, ::BytecodesReplyBytesElement))
}




/**
 * Component for [BytecodesReply]
 */
class BytecodesReplyBytesElement : JDWPComposite() {
    /**
     * A Java bytecode.
     */
    var bytecode by useField(JDWPByte())
}

