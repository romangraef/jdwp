@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPSingleContainer
import moe.nea.jdwp.JDWPSingleState
import moe.nea.jdwp.JDWPWriter

interface Tagged<N : Any> {
    val tag: N

    companion object {
        inline fun <reified T, N> lookupTable() where T : Enum<T>, T : Tagged<N>, N : Any =
            enumValues<T>().associateBy { it.tag }
    }
}

interface ByteTagged : Tagged<UByte>
interface ShortTagged : Tagged<Short>

class JDWPEnum<T : Any, N : Any>(val values: Map<N, T>, val delegate: JDWPSingleContainer<N>) : JDWPSingleState<T>() {
    val inverseValues = values.entries.associate { (k, v) -> v to k }

    companion object {
        inline operator fun <reified T, N : Any> invoke(
            delegate: JDWPSingleContainer<N>,
            default: T? = null
        ): JDWPEnum<T, N>
                where T : Tagged<N>, T : Enum<T> = of(delegate, default)

        inline  fun <reified T> ofByteTagged(default: T? = null): JDWPEnum<T, UByte>
                where T : ByteTagged, T : Enum<T> = of(JDWPByte(), default)

        inline  fun <reified T> ofShortTagged(default: T? = null): JDWPEnum<T, Short>
                where T : ShortTagged, T : Enum<T> = of(JDWPShort(), default)

        inline fun <reified T, N : Any> of(delegate: JDWPSingleContainer<N>, default: T? = null): JDWPEnum<T, N>
                where T : Tagged<N>, T : Enum<T> {
            return JDWPEnum(enumValues<T>().associateBy { it.tag }, delegate).also {
                it.value = default
            }
        }
    }

    override fun read(reader: JDWPReader) {
        delegate.read(reader)
        value = values[delegate.value] ?: error("Invalid TypeTag")
    }

    override fun write(writer: JDWPWriter) {
        delegate.value = inverseValues[value!!]
        delegate.write(writer)
    }
}