package moe.nea.jdwp.struct.method

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns line number information for the method, if present. The line table maps source line numbers to the initial code index of the line. The line table is ordered by code index (from lowest to highest). The line number information is constant unless a new class definition is installed using .
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_Method_LineTable)
 */
class LineTable : JDWPComposite(), JDWPCommandPayload<LineTableReply> {
    /**
     * The class.
     */
    var refType by useField(JDWPReferenceTypeId())
    /**
     * The method.
     */
    var methodID by useField(JDWPMethodId())
    override val reply = LineTableReply()
    override val commandId: UByte get() = 1.toUByte()
    override val commandSetId: UByte get() = 6.toUByte()
}
/**
 * Reply for [LineTable]
 */
class LineTableReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Lowest valid code index for the method, >=0, or -1 if the method is native
     */
    var start by useField(JDWPLong())
    /**
     * Highest valid code index for the method, >=0, or -1 if the method is native
     */
    var end by useField(JDWPLong())
    /**
     * The number of entries in the line table for this method.
     */
    var lines by useField(JDWPInt())
    var linesElements by useField(JDWPExternalVector(this::lines, ::LineTableReplyLinesElement))
}






/**
 * Component for [LineTableReply]
 */
class LineTableReplyLinesElement : JDWPComposite() {
    /**
     * Initial code index of the line, start <= lineCodeIndex < end
     */
    var lineCodeIndex by useField(JDWPLong())
    /**
     * Line number.
     */
    var lineNumber by useField(JDWPInt())
}


