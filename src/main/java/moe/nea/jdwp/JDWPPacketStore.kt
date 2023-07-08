package moe.nea.jdwp

import moe.nea.jdwp.struct.base.JDWPCommandPayload

interface JDWPPacketStore {
    fun makeUninitializedPayload(commandSet: UByte, command: UByte): JDWPCommandPayload<*>?

    companion object {
        fun empty(): JDWPPacketStore = object : JDWPPacketStore {
            override fun makeUninitializedPayload(commandSet: UByte, command: UByte): JDWPCommandPayload<*>? = null
        }

        fun ofPackets(vararg packets: () -> JDWPCommandPayload<*>): JDWPPacketStore {
            val dict = packets.associate { constructor ->
                val instance = constructor()
                Pair(instance.commandSetId, instance.commandId) to Pair(instance, constructor)
            }.toMutableMap()
            return object : JDWPPacketStore {
                override fun makeUninitializedPayload(commandSet: UByte, command: UByte): JDWPCommandPayload<*>? {
                    val key = Pair(commandSet, command)
                    val (payload, next) = dict[key] ?: return null
                    dict[key] = Pair(next(), next)
                    return payload
                }
            }
        }
    }
}