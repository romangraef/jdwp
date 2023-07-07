package moe.nea.jdwp.struct.base

import moe.nea.jdwp.JDWPElement

interface JDWPPayload : JDWPElement
interface JDWPCommandPayload : JDWPPayload {
    val commandSet: Byte
    val command: Byte
}

interface JDWPReplyPayload : JDWPPayload {
    val inReplyToCommandSet: Byte
    val inReplyToCommand: Byte
}