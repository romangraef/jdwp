package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns instances of this reference type. Only instances that are reachable for the purposes of garbage collection are returned. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ReferenceType_Instances)
 */
class Instances : JDWPComposite(), JDWPCommandPayload<InstancesReply> {
    /**
     * The reference type ID.
     */
    var refType by useField(JDWPReferenceTypeId())
    /**
     * Maximum number of instances to return.  Must be non-negative. If zero, all instances are returned.
     */
    var maxInstances by useField(JDWPInt())
    override val reply = InstancesReply()
    override val commandId: UByte get() = 16.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [Instances]
 */
class InstancesReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The number of instances that follow.
     */
    var instances by useField(JDWPInt())
    var instancesElements by useField(JDWPExternalVector(this::instances, ::InstancesReplyInstancesElement))
}




/**
 * Component for [InstancesReply]
 */
class InstancesReplyInstancesElement : JDWPComposite() {
    /**
     * An instance of this reference type.
     */
    val instance by useField(JDWPTaggedObjectId())
}

