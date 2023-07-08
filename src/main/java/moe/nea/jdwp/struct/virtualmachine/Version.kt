package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the JDWP version implemented by the target VM. The version string format is implementation dependent.
 */
class Version : JDWPComposite(), JDWPCommandPayload<VersionReply> {
    override val reply = VersionReply()
    override val commandId: UByte get() = 1.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [Version]
 */
class VersionReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Text information on the VM version
     */
    var description by useField(JDWPString())
    /**
     * Major JDWP Version number
     */
    var jdwpMajor by useField(JDWPInt())
    /**
     * Minor JDWP Version number
     */
    var jdwpMinor by useField(JDWPInt())
    /**
     * Target VM JRE version, as in the java.version property
     */
    var vmVersion by useField(JDWPString())
    /**
     * Target VM name, as in the java.vm.name property
     */
    var vmName by useField(JDWPString())
}





