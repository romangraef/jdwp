package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPSingleContainer

class JDWPUntaggedValue : JDWPSingleContainer<Long> by JDWPLong()