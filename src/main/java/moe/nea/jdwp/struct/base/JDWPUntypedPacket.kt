@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.struct.base

class JDWPUntypedPacket(header: PacketHeader = PacketHeader()) : JDWPPacket(header) {
    var contents by this::_contents
}