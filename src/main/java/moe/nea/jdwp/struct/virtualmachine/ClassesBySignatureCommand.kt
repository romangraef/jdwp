package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.JDWPComposite
import moe.nea.jdwp.primitives.JDWPString
import moe.nea.jdwp.struct.base.JDWPCommandPayload

/**
 *  Returns reference types for all the classes loaded by the target VM which match the given signature. Multple reference types will be returned if two or more class loaders have loaded a class of the same name. The search is confined to loaded classes only; no attempt is made to load a class of the given signature.
 */
class ClassesBySignatureCommand : JDWPComposite(), JDWPCommandPayload {
    override val commandSet: Byte
        get() = 1
    override val command: Byte
        get() = 2

    /**
     * JNI signature of the class to find (for example, "Ljava/lang/String;").
     */
    var signature by useField(JDWPString())
}