package moe.nea.jdwp.primitives

import moe.nea.jdwp.*

class JDWPFrameId private constructor(val delegate: JDWPPrimitiveVariableSizeLong) :
    JDWPSingleContainer<Long> by delegate {
    constructor() : this(JDWPPrimitiveVariableSizeLong(JDWPIDSizes::frameIdSize))
    constructor(value: Long) : this(JDWPPrimitiveVariableSizeLong(JDWPIDSizes::frameIdSize, value))
}