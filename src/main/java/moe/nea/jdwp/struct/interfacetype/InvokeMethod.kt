package moe.nea.jdwp.struct.interfacetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Invokes a static method. The method must not be a static initializer. The method must be a member of the interface type. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_InterfaceType_InvokeMethod)
 */
class InvokeMethod : JDWPComposite(), JDWPCommandPayload<InvokeMethodReply> {
    /**
     * The interface type ID.
     */
    var clazz by useField(JDWPInterfaceId())
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
     * Invocation [options](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_InvokeOptions)
     */
    var options by useField(JDWPInt())
    override val reply = InvokeMethodReply()
    override val commandId: UByte get() = 1.toUByte()
    override val commandSetId: UByte get() = 5.toUByte()
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




