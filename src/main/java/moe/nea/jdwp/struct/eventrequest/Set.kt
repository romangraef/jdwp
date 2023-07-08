package moe.nea.jdwp.struct.eventrequest

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Set an event request. When the event described by this request occurs, an  is sent from the target VM. If an event occurs that has not been requested then it is not sent from the target VM. The two exceptions to this are the VM Start Event and the VM Death Event which are automatically generated events - see  for further details.
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_EventRequest_Set)
 */
/**
 * Reply for [Set]
 */
class SetReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * ID of created request
     */
    var requestID by useField(JDWPInt())
}

