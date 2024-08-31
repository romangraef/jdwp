package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the classes and interfaces directly nested within this type.Types further nested within those types are not included. 
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ReferenceType_NestedTypes)
 */
class NestedTypes : JDWPComposite(), JDWPCommandPayload<NestedTypesReply> {
    /**
     * The reference type ID.
     */
    var refType by useField(JDWPReferenceTypeId())
    override val reply = NestedTypesReply()
    override val commandId: UByte get() = 8.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [NestedTypes]
 */
class NestedTypesReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The number of nested classes and interfaces
     */
    var classes by useField(JDWPInt())
    var classesElements by useField(JDWPExternalVector(this::classes, ::NestedTypesReplyClassesElement))
}



/**
 * Component for [NestedTypesReply]
 */
class NestedTypesReplyClassesElement : JDWPComposite() {
    /**
     * [Kind](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_TypeTag) of following reference type.
     */
    var refTypeTag by useField(JDWPByte())
    /**
     * The nested class or interface ID.
     */
    var typeID by useField(JDWPReferenceTypeId())
}


