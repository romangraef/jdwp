package moe.nea.jdwp

import moe.nea.jdwp.struct.base.JDWPCommandPayload

interface JDWPPacketStore {
    fun makeUninitializedPayload(commandSet: UByte, command: UByte): JDWPCommandPayload?
}