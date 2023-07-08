package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the class file major and minor version numbers, as defined in the class file format of the Java Virtual Machine specification. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ReferenceType_ClassFileVersion)
 */
class ClassFileVersion : JDWPComposite(), JDWPCommandPayload<ClassFileVersionReply> {
    /**
     * The class.
     */
    var refType by useField(JDWPReferenceTypeId())
    override val reply = ClassFileVersionReply()
    override val commandId: UByte get() = 17.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [ClassFileVersion]
 */
class ClassFileVersionReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Major version number
     */
    var majorVersion by useField(JDWPInt())
    /**
     * Minor version number
     */
    var minorVersion by useField(JDWPInt())
}



