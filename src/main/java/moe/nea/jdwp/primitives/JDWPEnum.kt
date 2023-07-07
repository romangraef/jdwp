@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPSingleState
import moe.nea.jdwp.JDWPWriter

interface ByteTagged {
    val byteTag: UByte
}

class JDWPEnum<T : ByteTagged>(val values: Map<UByte, T>) : JDWPSingleState<T>() {
    companion object {
        inline operator fun <reified T> invoke(default: T? = null): JDWPEnum<T>
                where T : ByteTagged, T : Enum<T> = of(default)

        inline fun <reified T> of(default: T? = null): JDWPEnum<T>
                where T : ByteTagged, T : Enum<T> {
            return JDWPEnum(enumValues<T>().associateBy { it.byteTag }).also {
                it.value = default
            }
        }
    }

    override fun read(reader: JDWPReader) {
        value = values[reader.consume(1)[0]] ?: error("Invalid TypeTag")
    }

    override fun write(writer: JDWPWriter) {
        writer.append(ubyteArrayOf(value!!.byteTag))
    }
}