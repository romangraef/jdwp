package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Return the raw bytes of the constant pool in the format of the constant_pool item of the Class File Format in . 
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ReferenceType_ConstantPool)
 */
class ConstantPool : JDWPComposite(), JDWPCommandPayload<ConstantPoolReply> {
    /**
     * The class.
     */
    var refType by useField(JDWPReferenceTypeId())
    override val reply = ConstantPoolReply()
    override val commandId: UByte get() = 18.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [ConstantPool]
 */
class ConstantPoolReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Total number of constant pool entries plus one. This corresponds to the constant_pool_count item of the Class File Format in The Java Virtual Machine Specification.
     */
    var count by useField(JDWPInt())
    var bytes by useField(JDWPInt())
    var bytesElements by useField(JDWPExternalVector(this::bytes, ::ConstantPoolReplyBytesElement))
}




/**
 * Component for [ConstantPoolReply]
 */
class ConstantPoolReplyBytesElement : JDWPComposite() {
    /**
     * Raw bytes of constant pool
     */
    var cpbytes by useField(JDWPByte())
}

