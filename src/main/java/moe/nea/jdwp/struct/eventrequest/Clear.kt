package moe.nea.jdwp.struct.eventrequest

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Clear an event request. See  for a complete list of events that can be cleared. Only the event request matching the specified event kind and requestID is cleared. If there isn't a matching event request the command is a no-op and does not result in an error. Automatically generated events do not have a corresponding event request and may not be cleared using this command.
 */
class Clear : JDWPComposite(), JDWPCommandPayload<ClearReply> {
    /**
     * Event kind to clear
     */
    var eventKind by useField(JDWPByte())
    /**
     * ID of request to clear
     */
    var requestID by useField(JDWPInt())
    override val reply = ClearReply()
    override val commandId: UByte get() = 2.toUByte()
    override val commandSetId: UByte get() = 15.toUByte()
}
/**
 * Reply for [Clear]
 */
class ClearReply : JDWPComposite(), JDWPReplyPayload {
}


