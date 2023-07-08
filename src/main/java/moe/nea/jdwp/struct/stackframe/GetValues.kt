package moe.nea.jdwp.struct.stackframe

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the value of one or more local variables in a given frame. Each variable must be visible at the frame's code index. Even if local variable information is not available, values can be retrieved if the front-end is able to determine the correct local variable index. (Typically, this index can be determined for method arguments from the method signature without access to the local variable table information.) 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_StackFrame_GetValues)
 */
class GetValues : JDWPComposite(), JDWPCommandPayload<GetValuesReply> {
    /**
     * The frame's thread.
     */
    var thread by useField(JDWPThreadId())
    /**
     * The frame ID.
     */
    var frame by useField(JDWPFrameId())
    /**
     * The number of values to get.
     */
    var slots by useField(JDWPInt())
    var slotsElements by useField(JDWPExternalVector(this::slots, ::GetValuesSlotsElement))
    override val reply = GetValuesReply()
    override val commandId: UByte get() = 1.toUByte()
    override val commandSetId: UByte get() = 16.toUByte()
}
/**
 * Reply for [GetValues]
 */
class GetValuesReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The number of values retrieved, always equal to slots, the number of values to get.
     */
    var values by useField(JDWPInt())
    var valuesElements by useField(JDWPExternalVector(this::values, ::GetValuesReplyValuesElement))
}




/**
 * Component for [GetValues]
 */
class GetValuesSlotsElement : JDWPComposite() {
    /**
     * The local variable's index in the frame.
     */
    var slot by useField(JDWPInt())
    /**
     * A tag identifying the type of the variable
     */
    var sigbyte by useField(JDWPByte())
}




/**
 * Component for [GetValuesReply]
 */
class GetValuesReplyValuesElement : JDWPComposite() {
    /**
     * The value of the local variable.
     */
    val slotValue by useField(JDWPValue())
}

