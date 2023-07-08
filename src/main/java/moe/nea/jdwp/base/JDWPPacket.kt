@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.base

import moe.nea.jdwp.JDWPComposite
import moe.nea.jdwp.primitives.JDWPByteArray

sealed class JDWPPacket(header: PacketHeader = PacketHeader()) : JDWPComposite() {
    val header by useField(header)
    internal var _contents by useField(
        JDWPByteArray(
            { header.length - PacketHeader.PACKET_HEADER_SIZE },
            { header.length = it + PacketHeader.PACKET_HEADER_SIZE })
    )
}
