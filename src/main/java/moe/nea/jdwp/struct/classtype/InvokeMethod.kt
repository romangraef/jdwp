package moe.nea.jdwp.struct.classtype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Invokes a static method. The method must be member of the class type or one of its superclasses. Access control is not enforced; for example, private methods can be invoked.
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ClassType_InvokeMethod)
 */
class InvokeMethod : JDWPComposite(), JDWPCommandPayload<InvokeMethodReply> {
    /**
     * The class type ID.
     */
    var clazz by useField(JDWPClassId())
    /**
     * The thread in which to invoke.
     */
    var thread by useField(JDWPThreadId())
    /**
     * The method to invoke.
     */
    var methodID by useField(JDWPMethodId())
    var arguments by useField(JDWPInt())
    var argumentsElements by useField(JDWPExternalVector(this::arguments, ::InvokeMethodArgumentsElement))
    /**
     * Invocation [options](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_InvokeOptions)
     */
    var options by useField(JDWPInt())
    override val reply = InvokeMethodReply()
    override val commandId: UByte get() = 3.toUByte()
    override val commandSetId: UByte get() = 3.toUByte()
}
/**
 * Reply for [InvokeMethod]
 */
class InvokeMethodReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The returned value.
     */
    val returnValue by useField(JDWPValue())
    /**
     * The thrown exception.
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




