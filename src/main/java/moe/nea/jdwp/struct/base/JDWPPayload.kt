package moe.nea.jdwp.struct.base

import moe.nea.jdwp.JDWPElement

interface JDWPPayload : JDWPElement
interface JDWPCommandPayload : JDWPPayload {
    val reply: JDWPReplyPayload
    val commandId: Byte
    val commandSetId: Byte
}

interface JDWPReplyPayload : JDWPPayload