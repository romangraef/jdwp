package moe.nea.jdwp.struct.objectreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Prevents garbage collection for the given object. By default all objects in back-end replies may be collected at any time the target VM is running. A call to this command guarantees that the object will not be collected. The  command can be used to allow collection once again.
 */
class DisableCollection : JDWPComposite(), JDWPCommandPayload<DisableCollectionReply> {
    /**
     * The object ID
     */
    var `object` by useField(JDWPObjectId())
    override val reply = DisableCollectionReply()
    override val commandId: UByte get() = 7.toUByte()
    override val commandSetId: UByte get() = 9.toUByte()
}
/**
 * Reply for [DisableCollection]
 */
class DisableCollectionReply : JDWPComposite(), JDWPReplyPayload {
}

