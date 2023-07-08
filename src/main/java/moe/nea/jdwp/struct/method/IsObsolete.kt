package moe.nea.jdwp.struct.method

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Determine if this method is obsolete. A method is obsolete if it has been replaced by a non-equivalent method using the  command. The original and redefined methods are considered equivalent if their bytecodes are the same except for indices into the constant pool and the referenced constants are equal.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_Method_IsObsolete)
 */
class IsObsolete : JDWPComposite(), JDWPCommandPayload<IsObsoleteReply> {
    /**
     * The class.
     */
    var refType by useField(JDWPReferenceTypeId())
    /**
     * The method.
     */
    var methodID by useField(JDWPMethodId())
    override val reply = IsObsoleteReply()
    override val commandId: UByte get() = 4.toUByte()
    override val commandSetId: UByte get() = 6.toUByte()
}
/**
 * Reply for [IsObsolete]
 */
class IsObsoleteReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * true if this method has been replacedby a non-equivalent method usingthe RedefineClasses command.
     */
    var isObsolete by useField(JDWPBoolean())
}



