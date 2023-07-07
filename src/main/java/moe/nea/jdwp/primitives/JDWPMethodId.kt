package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPIDSizes
import moe.nea.jdwp.JDWPPrimitiveVariableSizeLong
import moe.nea.jdwp.JDWPSingleContainer

class JDWPMethodId private constructor(val delegate: JDWPPrimitiveVariableSizeLong) :
    JDWPSingleContainer<Long> by delegate {
    constructor() : this(JDWPPrimitiveVariableSizeLong(JDWPIDSizes::methodIdSize))
    constructor(value: Long) : this(JDWPPrimitiveVariableSizeLong(JDWPIDSizes::methodIdSize, value))

    override fun toString(): String {
        return "JDWPMethodId($value)"
    }
}