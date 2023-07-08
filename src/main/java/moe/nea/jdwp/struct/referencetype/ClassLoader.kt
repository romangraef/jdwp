package moe.nea.jdwp.struct.referencetype

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the instance of java.lang.ClassLoader which loaded a given reference type. If the reference type was loaded by the system class loader, the returned object ID is null.
 */
class ClassLoader : JDWPComposite(), JDWPCommandPayload<ClassLoaderReply> {
    /**
     * The reference type ID.
     */
    var refType by useField(JDWPReferenceTypeId())
    override val reply = ClassLoaderReply()
    override val commandId: UByte get() = 2.toUByte()
    override val commandSetId: UByte get() = 2.toUByte()
}
/**
 * Reply for [ClassLoader]
 */
class ClassLoaderReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The class loader for the reference type.
     */
    var classLoader by useField(JDWPClassLoaderId())
}


