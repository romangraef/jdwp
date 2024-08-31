package moe.nea.jdwp.struct.objectreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Invokes a instance method. The method must be member of the object's type or one of its superclasses, superinterfaces, or implemented interfaces. Access control is not enforced; for example, private methods can be invoked.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ObjectReference_InvokeMethod)
 */
class InvokeMethod : JDWPComposite(), JDWPCommandPayload<InvokeMethodReply> {
    /**
     * The object ID
     */
    var `object` by useField(JDWPObjectId())
    /**
     * The thread in which to invoke.
     */
    var thread by useField(JDWPThreadId())
    /**
     * The class type.
     */
    var clazz by useField(JDWPClassId())
    /**
     * The method to invoke.
     */
    var methodID by useField(JDWPMethodId())
    /**
     * The number of arguments.
     */
    var arguments by useField(JDWPInt())
    var argumentsElements by useField(JDWPExternalVector(this::arguments, ::InvokeMethodArgumentsElement))
    /**
     * Invocation [options](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_InvokeOptions)
     */
    var options by useField(JDWPInt())
    override val reply = InvokeMethodReply()
    override val commandId: UByte get() = 6.toUByte()
    override val commandSetId: UByte get() = 9.toUByte()
}
/**
 * Reply for [InvokeMethod]
 */
class InvokeMethodReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The returned value, or null if an exception is thrown.
     */
    val returnValue by useField(JDWPValue())
    /**
     * The thrown exception, if any.
     */
    val exception by useField(JDWPTaggedObjectId())
}






/**
 * Component for [InvokeMethod]
 */
class InvokeMethodArgumentsElement : JDWPComposite() {
    /**
     * The argument value.
     */
    val arg by useField(JDWPValue())
}




