package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPIDSizes
import moe.nea.jdwp.JDWPPrimitiveVariableSizeLong
import moe.nea.jdwp.JDWPSingleContainer

class JDWPClassObjectId private constructor(val delegate: JDWPPrimitiveVariableSizeLong) :
    JDWPSingleContainer<Long> by delegate {
    constructor() : this(JDWPPrimitiveVariableSizeLong(JDWPIDSizes::objectIdSize))
    constructor(value: Long) : this(JDWPPrimitiveVariableSizeLong(JDWPIDSizes::objectIdSize, value))
}