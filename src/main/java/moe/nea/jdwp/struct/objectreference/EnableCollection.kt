package moe.nea.jdwp.struct.objectreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Permits garbage collection for this object. By default all objects returned by JDWP may become unreachable in the target VM, and hence may be garbage collected. A call to this command is necessary only if garbage collection was previously disabled with the  command.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ObjectReference_EnableCollection)
 */
class EnableCollection : JDWPComposite(), JDWPCommandPayload<EnableCollectionReply> {
    /**
     * The object ID
     */
    var `object` by useField(JDWPObjectId())
    override val reply = EnableCollectionReply()
    override val commandId: UByte get() = 8.toUByte()
    override val commandSetId: UByte get() = 9.toUByte()
}
/**
 * Reply for [EnableCollection]
 */
class EnableCollectionReply : JDWPComposite(), JDWPReplyPayload {
}

