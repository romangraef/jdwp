package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPIDSizes
import moe.nea.jdwp.JDWPPrimitiveVariableSizeLong
import moe.nea.jdwp.JDWPSingleContainer

class JDWPClassId private constructor(val delegate: JDWPPrimitiveVariableSizeLong) :
    JDWPSingleContainer<Long> by delegate {
    constructor() : this(JDWPPrimitiveVariableSizeLong(JDWPIDSizes::referenceTypeIdSize))
    constructor(value: Long) : this(JDWPPrimitiveVariableSizeLong(JDWPIDSizes::referenceTypeIdSize, value))

    override fun toString(): String {
        return "JDWPClassId($value)"
    }
}