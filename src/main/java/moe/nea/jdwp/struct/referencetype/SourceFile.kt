package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.base.*

/**
 * Returns the name of source file in which a reference type was declared. 
 * [External](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#JDWP_ReferenceType_SourceFile)
 */
class SourceFile : JDWPComposite(), JDWPCommandPayload<SourceFileReply> {
    /**
     * The reference type ID.
     */
    var refType by useField(JDWPReferenceTypeId())
    override val reply = SourceFileReply()
    override val commandId: UByte get() = 7.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [SourceFile]
 */
class SourceFileReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The source file name. No path information for the file is included
     */
    var sourceFile by useField(JDWPString())
}


