package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPComposite
import moe.nea.jdwp.JDWPPrimitiveVariableSizeLong
import moe.nea.jdwp.struct.base.JDWPTagConstants

class JDWPValue : JDWPComposite() {
    var typeTag by useField(JDWPEnum.ofByteTagged<JDWPTagConstants>())

    /**
     * Depending on the value of type tag, contains either an object id, string id, thread id, class loader id, class object id, or array id.
     * It can also contain the raw bytes of the content, in case of a primitive, in which case those bytes are stored
     * in the least significant places. For void no data is stored. There is no guarantee made about the other bits.
     * For floating point values, the bits are stored, not the closest long value.
     *
     * @see Double.toBits
     * @see Double.Companion.fromBits
     * @see Float.toBits
     * @see Float.Companion.fromBits
     */
    var reference: Long by useField(JDWPPrimitiveVariableSizeLong { typeTag.byteWidth ?: it.objectIdSize })
}