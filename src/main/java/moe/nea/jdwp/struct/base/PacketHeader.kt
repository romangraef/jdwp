package moe.nea.jdwp.struct.base

import moe.nea.jdwp.JDWPComposite
import moe.nea.jdwp.primitives.JDWPByte
import moe.nea.jdwp.primitives.JDWPInt
import moe.nea.jdwp.primitives.JDWPShort
import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or

class PacketHeader : JDWPComposite() {
    /**
     * Length of this packet, including the 11 bytes of the header field
     */
    var length by useField(JDWPInt())
    var replyId by useField(JDWPInt())
    var flags by useField(JDWPByte())
    var commandOrErrorCode by useField(JDWPShort())

    var isReply: Boolean
        get() = (flags and 0x80.toByte()) != 0.toByte()
        set(value) {
            flags = if (value)
                flags or 0x80.toByte()
            else
                flags and (0x80.toByte().inv())
        }
}