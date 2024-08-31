package moe.nea.jdwp.struct.classtype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Creates a new object of this type, invoking the specified constructor. The constructor method ID must be a member of the class type.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ClassType_NewInstance)
 */
class NewInstance : JDWPComposite(), JDWPCommandPayload<NewInstanceReply> {
    /**
     * The class type ID.
     */
    var clazz by useField(JDWPClassId())
    /**
     * The thread in which to invoke the constructor.
     */
    var thread by useField(JDWPThreadId())
    /**
     * The constructor to invoke.
     */
    var methodID by useField(JDWPMethodId())
    var arguments by useField(JDWPInt())
    var argumentsElements by useField(JDWPExternalVector(this::arguments, ::NewInstanceArgumentsElement))
    /**
     * Constructor invocation [options](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_InvokeOptions)
     */
    var options by useField(JDWPInt())
    override val reply = NewInstanceReply()
    override val commandId: UByte get() = 4.toUByte()
    override val commandSetId: UByte get() = 3.toUByte()
}
/**
 * Reply for [NewInstance]
 */
class NewInstanceReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The newly created object, or null if the constructor threw an exception.
     */
    val newObject by useField(JDWPTaggedObjectId())
    /**
     * The thrown exception, if any; otherwise, null.
     */
    val exception by useField(JDWPTaggedObjectId())
}





/**
 * Component for [NewInstance]
 */
class NewInstanceArgumentsElement : JDWPComposite() {
    /**
     * The argument value.
     */
    val arg by useField(JDWPValue())
}




