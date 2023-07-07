package moe.nea.jdwp

import moe.nea.jdwp.struct.base.JDWPCommandPayload

interface JDWPPacketStore {
    fun makeUninitializedPayload(commandSet: Byte, command: Byte): JDWPCommandPayload?
}