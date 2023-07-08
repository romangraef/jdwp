package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Releases a list of object IDs. For each object in the list, the following applies. The count of references held by the back-end (the reference count) will be decremented by refCnt. If thereafter the reference count is less than or equal to zero, the ID is freed. Any back-end resources associated with the freed ID may be freed, and if garbage collection was disabled for the object, it will be re-enabled. The sender of this command promises that no further commands will be sent referencing a freed ID.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_VirtualMachine_DisposeObjects)
 */
class DisposeObjects : JDWPComposite(), JDWPCommandPayload<DisposeObjectsReply> {
    /**
     * Number of object dispose requests that follow
     */
    var requests by useField(JDWPInt())
    var requestsElements by useField(JDWPExternalVector(this::requests, ::DisposeObjectsRequestsElement))
    override val reply = DisposeObjectsReply()
    override val commandId: UByte get() = 14.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [DisposeObjects]
 */
class DisposeObjectsReply : JDWPComposite(), JDWPReplyPayload {
}


/**
 * Component for [DisposeObjects]
 */
class DisposeObjectsRequestsElement : JDWPComposite() {
    /**
     * The object ID
     */
    var `object` by useField(JDWPObjectId())
    /**
     * The number of times this object ID has been part of a packet received from the back-end. An accurate count prevents the object ID from being freed on the back-end if it is part of an incoming packet, not yet handled by the front-end.
     */
    var refCnt by useField(JDWPInt())
}


