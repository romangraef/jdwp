package moe.nea.jdwp

import moe.nea.jdwp.struct.base.JDWPPacket

interface JDWPPacketStore {
    fun makeUninitializedPacket(commandSet: Byte, command: Byte): JDWPPacket
}