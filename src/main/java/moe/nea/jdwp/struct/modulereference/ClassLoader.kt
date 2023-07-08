package moe.nea.jdwp.struct.modulereference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns the class loader of this module.
 */
class ClassLoader : JDWPComposite(), JDWPCommandPayload<ClassLoaderReply> {
    /**
     * This module.
     */
    var module by useField(JDWPModuleId())
    override val reply = ClassLoaderReply()
    override val commandId: UByte get() = 2.toUByte()
    override val commandSetId: UByte get() = 18.toUByte()
}
/**
 * Reply for [ClassLoader]
 */
class ClassLoaderReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The module's class loader.
     */
    var classLoader by useField(JDWPClassLoaderId())
}


