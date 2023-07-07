@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.struct.base

import moe.nea.jdwp.JDWPComposite
import moe.nea.jdwp.primitives.JDWPByteArray

sealed class JDWPPacket : JDWPComposite() {
    val header by useField(PacketHeader())
    internal var _contents by useField(
        JDWPByteArray(
            { header.length - PacketHeader.PACKET_HEADER_SIZE },
            { header.length = it + PacketHeader.PACKET_HEADER_SIZE })
    )
}
