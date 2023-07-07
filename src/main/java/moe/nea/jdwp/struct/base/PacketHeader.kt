package moe.nea.jdwp.struct.base

import moe.nea.jdwp.JDWPComposite
import moe.nea.jdwp.primitives.JDWPByte
import moe.nea.jdwp.primitives.JDWPInt
import moe.nea.jdwp.primitives.JDWPShort

class PacketHeader : JDWPComposite() {
    companion object {
        const val PACKET_HEADER_SIZE: Int = 11
    }

    /**
     * Length of this packet, including the 11 bytes of the header field
     */
    var length by useField(JDWPInt(11))
    var replyId by useField(JDWPInt())
    var flags by useField(JDWPByte())
    var commandOrErrorCode by useField(JDWPShort())

    var isReply: Boolean
        get() = (flags and 0x80.toUByte()) != 0.toUByte()
        set(value) {
            flags = if (value)
                flags or 0x80.toUByte()
            else
                flags and (0x80.toUByte().inv())
        }

    /**
     * This is stored in [commandOrErrorCode]. Accessing this while the [isReply] bit isn't set, will result in
     * unexpected behaviour. Note that `null` indicates an unknown error code, and not that no error occurred
     * (Use [JDWPErrorCode.NONE] for that instead).
     */
    var errorCode: JDWPErrorCode?
        get() = JDWPErrorCode.fromShort(commandOrErrorCode)
        set(value) {
            require(value != null)
            commandOrErrorCode = value.tag
        }

}