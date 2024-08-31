package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the interfaces declared as implemented by this class. Interfaces indirectly implemented (extended by the implemented interface or implemented by a superclass) are not included.
 * [External](https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html#JDWP_ReferenceType_Interfaces)
 */
class Interfaces : JDWPComposite(), JDWPCommandPayload<InterfacesReply> {
    /**
     * The reference type ID.
     */
    var refType by useField(JDWPReferenceTypeId())
    override val reply = InterfacesReply()
    override val commandId: UByte get() = 10.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [Interfaces]
 */
class InterfacesReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The number of implemented interfaces
     */
    var interfaces by useField(JDWPInt())
    var interfacesElements by useField(JDWPExternalVector(this::interfaces, ::InterfacesReplyInterfacesElement))
}



/**
 * Component for [InterfacesReply]
 */
class InterfacesReplyInterfacesElement : JDWPComposite() {
    /**
     * implemented interface.
     */
    var interfaceType by useField(JDWPInterfaceId())
}

