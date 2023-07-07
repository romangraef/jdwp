package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.JDWPComposite
import moe.nea.jdwp.primitives.JDWPInt
import moe.nea.jdwp.primitives.JDWPString
import moe.nea.jdwp.struct.base.JDWPCommandPayload
import moe.nea.jdwp.struct.base.JDWPReplyPayload

/**
 * Returns the JDWP version implemented by the target VM. The version string format is implementation dependent.
 */
class Version : JDWPComposite(), JDWPCommandPayload {
    override val reply = VersionReply()
    override val commandId: Byte get() = 1
    override val commandSetId: Byte get() = 1
}

/**
 * Reply for [Version]
 */
class VersionReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Text information on the VM version
     */
    val description by useField(JDWPString())

    /**
     * Major JDWP Version number
     */
    val jdwpMajor by useField(JDWPInt())

    /**
     * Minor JDWP Version number
     */
    val jdwpMinor by useField(JDWPInt())

    /**
     * Target VM JRE version, as in the java.version property
     */
    val vmVersion by useField(JDWPString())

    /**
     * Target VM name, as in the java.vm.name property
     */
    val vmName by useField(JDWPString())
}


