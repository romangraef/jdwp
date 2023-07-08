package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the number of instances of each reference type in the input list. Only instances that are reachable for the purposes of garbage collection are counted.  If a reference type is invalid, eg. it has been unloaded, zero is returned for its instance count.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_InstanceCounts)
 */
class InstanceCounts : JDWPComposite(), JDWPCommandPayload<InstanceCountsReply> {
    /**
     * Number of reference types that follow.    Must be non-negative.
     */
    var refTypesCount by useField(JDWPInt())
    var refTypesCountElements by useField(JDWPExternalVector(this::refTypesCount, ::InstanceCountsRefTypesCountElement))
    override val reply = InstanceCountsReply()
    override val commandId: UByte get() = 21.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [InstanceCounts]
 */
class InstanceCountsReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The number of counts that follow.
     */
    var counts by useField(JDWPInt())
    var countsElements by useField(JDWPExternalVector(this::counts, ::InstanceCountsReplyCountsElement))
}


/**
 * Component for [InstanceCounts]
 */
class InstanceCountsRefTypesCountElement : JDWPComposite() {
    /**
     * A reference type ID.
     */
    var refType by useField(JDWPReferenceTypeId())
}



/**
 * Component for [InstanceCountsReply]
 */
class InstanceCountsReplyCountsElement : JDWPComposite() {
    /**
     * The number of instances for the corresponding reference type in 'Out Data'.
     */
    var instanceCount by useField(JDWPLong())
}

