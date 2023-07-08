package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns reference types for all the classes loaded by the target VM which match the given signature. Multple reference types will be returned if two or more class loaders have loaded a class of the same name. The search is confined to loaded classes only; no attempt is made to load a class of the given signature.
 */
class ClassesBySignature : JDWPComposite(), JDWPCommandPayload<ClassesBySignatureReply> {
    /**
     * JNI signature of the class to find (for example, "Ljava/lang/String;").
     */
    var signature by useField(JDWPString())
    override val reply = ClassesBySignatureReply()
    override val commandId: UByte get() = 2.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [ClassesBySignature]
 */
class ClassesBySignatureReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Number of reference types that follow.
     */
    var classes by useField(JDWPInt())
    var classesElements by useField(JDWPExternalVector(this::classes, ::ClassesBySignatureClassesElement))
}



class ClassesBySignatureClassesElement : JDWPComposite() {
    /**
     * Kind of following reference type.
     */
    var refTypeTag by useField(JDWPByte())
    /**
     * Matching loaded reference type
     */
    var typeID by useField(JDWPReferenceTypeId())
    /**
     * The current class status.
     */
    var status by useField(JDWPInt())
}



