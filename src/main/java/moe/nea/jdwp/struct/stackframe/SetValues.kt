package moe.nea.jdwp.struct.stackframe

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Sets the value of one or more local variables. Each variable must be visible at the current frame code index. For primitive values, the value's type must match the variable's type exactly. For object values, there must be a widening reference conversion from the value's type to thevariable's type and the variable's type must be loaded. 
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_StackFrame_SetValues)
 */
class SetValues : JDWPComposite(), JDWPCommandPayload<SetValuesReply> {
    /**
     * The frame's thread.
     */
    var thread by useField(JDWPThreadId())
    /**
     * The frame ID.
     */
    var frame by useField(JDWPFrameId())
    /**
     * The number of values to set.
     */
    var slotValues by useField(JDWPInt())
    var slotValuesElements by useField(JDWPExternalVector(this::slotValues, ::SetValuesSlotValuesElement))
    override val reply = SetValuesReply()
    override val commandId: UByte get() = 2.toUByte()
    override val commandSetId: UByte get() = 16.toUByte()
}
/**
 * Reply for [SetValues]
 */
class SetValuesReply : JDWPComposite(), JDWPReplyPayload {
}




/**
 * Component for [SetValues]
 */
class SetValuesSlotValuesElement : JDWPComposite() {
    /**
     * The slot ID.
     */
    var slot by useField(JDWPInt())
    /**
     * The value to set.
     */
    val slotValue by useField(JDWPValue())
}


