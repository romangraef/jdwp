@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.struct.base

class JDWPUntypedPacket : JDWPPacket() {
    var contents by this::_contents
}