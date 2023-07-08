@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea.jdwp

import moe.nea.jdwp.struct.virtualmachine.IDSizesReply

class JDWPIDSizes() : JDWPElement {
    fun setFrom(sizes: JDWPIDSizes) {
        this.fieldIdSize = sizes.fieldIdSize
        this.methodIdSize = sizes.methodIdSize
        this.objectIdSize = sizes.objectIdSize
        this.referenceTypeIdSize = sizes.referenceTypeIdSize
        this.frameIdSize = sizes.frameIdSize
    }

    fun setFrom(idSizesReply: IDSizesReply) {
        this.fieldIdSize = idSizesReply.fieldIDSize
        this.methodIdSize = idSizesReply.methodIDSize
        this.objectIdSize = idSizesReply.objectIDSize
        this.referenceTypeIdSize = idSizesReply.referenceTypeIDSize
        this.frameIdSize = idSizesReply.frameIDSize
    }

    companion object {
        fun standardSizes() = JDWPIDSizes().also {
            it.fieldIdSize = 8
            it.methodIdSize = 8
            it.objectIdSize = 8
            it.referenceTypeIdSize = 8
            it.frameIdSize = 8
        }
    }

    var fieldIdSize: Int = 0
        get() {
            if (field == 0) error("Accessing uninitialized Field Id size despite")
            return field
        }
    var methodIdSize: Int = 0
        get() {
            if (field == 0) error("Accessing uninitialized Method Id size despite")
            return field
        }
    var objectIdSize: Int = 0
        get() {
            if (field == 0) error("Accessing uninitialized Object Id size despite")
            return field
        }
    var referenceTypeIdSize: Int = 0
        get() {
            if (field == 0) error("Accessing uninitialized Reference Type Id size despite")
            return field
        }
    var frameIdSize: Int = 0
        get() {
            if (field == 0) error("Accessing uninitialized Frame Id size despite")
            return field
        }

    override fun read(reader: JDWPReader) {
        val (fi, m, o, r, fr) = reader.consume(5)
        fieldIdSize = fi.toInt()
        methodIdSize = m.toInt()
        objectIdSize = o.toInt()
        referenceTypeIdSize = r.toInt()
        frameIdSize = fr.toInt()
    }

    override fun write(writer: JDWPWriter) {
        writer.append(
            ubyteArrayOf(
                fieldIdSize.toUByte(),
                methodIdSize.toUByte(),
                objectIdSize.toUByte(),
                referenceTypeIdSize.toUByte(),
                frameIdSize.toUByte(),
            )
        )
    }

}