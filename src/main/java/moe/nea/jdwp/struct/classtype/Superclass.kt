package moe.nea.jdwp.struct.classtype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the immediate superclass of a class.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ClassType_Superclass)
 */
class Superclass : JDWPComposite(), JDWPCommandPayload<SuperclassReply> {
    /**
     * The class type ID.
     */
    var clazz by useField(JDWPClassId())
    override val reply = SuperclassReply()
    override val commandId: UByte get() = 1.toUByte()
    override val commandSetId: UByte get() = 3.toUByte()
}
/**
 * Reply for [Superclass]
 */
class SuperclassReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The superclass (null if the class ID for java.lang.Object is specified).
     */
    var superclass by useField(JDWPClassId())
}


