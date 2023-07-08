package moe.nea.jdwp.base

import moe.nea.jdwp.JDWPElement

interface JDWPPayload : JDWPElement
interface JDWPCommandPayload<T : JDWPReplyPayload> : JDWPPayload {
    val reply: T
    val commandId: UByte
    val commandSetId: UByte
}

interface JDWPReplyPayload : JDWPPayload