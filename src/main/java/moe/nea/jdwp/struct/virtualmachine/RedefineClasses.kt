package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Installs new class definitions. If there are active stack frames in methods of the redefined classes in the target VM then those active frames continue to run the bytecodes of the original method. These methods are considered obsolete - see [IsObsolete](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_Method_IsObsolete). The methods in the redefined classes will be used for new invokes in the target VM. The original method ID refers to the redefined method. All breakpoints in the redefined classes are cleared.If resetting of stack frames is desired, the [PopFrames](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_StackFrame_PopFrames) command can be used to pop frames with obsolete methods.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_RedefineClasses)
 */
class RedefineClasses : JDWPComposite(), JDWPCommandPayload<RedefineClassesReply> {
    /**
     * Number of reference types that follow.
     */
    var classes by useField(JDWPInt())
    var classesElements by useField(JDWPExternalVector(this::classes, ::RedefineClassesClassesElement))
    override val reply = RedefineClassesReply()
    override val commandId: UByte get() = 18.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [RedefineClasses]
 */
class RedefineClassesReply : JDWPComposite(), JDWPReplyPayload {
}


/**
 * Component for [RedefineClasses]
 */
class RedefineClassesClassesElement : JDWPComposite() {
    /**
     * The reference type.
     */
    var refType by useField(JDWPReferenceTypeId())
    /**
     * Number of bytes defining class (below)
     */
    var classfile by useField(JDWPInt())
    var classfileElements by useField(JDWPExternalVector(this::classfile, ::RedefineClassesClassfileElement))
}



/**
 * Component for [RedefineClasses]
 */
class RedefineClassesClassfileElement : JDWPComposite() {
    /**
     * byte in JVM class file format.
     */
    var classbyte by useField(JDWPByte())
}

