package moe.nea.jdwp.struct.base

import moe.nea.jdwp.primitives.ByteTagged

enum class JDWPTagConstants(
    override val tag: UByte,
    val descriptor: Char,
    val byteWidth: Int?,
) : ByteTagged {
    ARRAY('[', null),
    BYTE('B', 1),
    CHAR('C', 2),
    OBJECT('L', null),
    FLOAT('F', 4),
    DOUBLE('D', 8),
    INT('I', 4),
    LONG('J', 8),
    SHORT('S', 2),
    VOID('V', 0),
    BOOLEAN('Z', null),
    STRING('s', null),
    THREAD('t', null),
    THREAD_GROUP('g', null),
    CLASS_LOADER('l', null),
    CLASS_OBJECT('c', null),
    ;


    constructor(char: Char, byteWidth: Int?) : this(char.code.toByte().toUByte(), char, byteWidth)
}