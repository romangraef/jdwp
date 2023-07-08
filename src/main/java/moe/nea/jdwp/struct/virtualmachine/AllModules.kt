package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns all modules in the target VM.
 */
class AllModules : JDWPComposite(), JDWPCommandPayload<AllModulesReply> {
    override val reply = AllModulesReply()
    override val commandId: UByte get() = 22.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [AllModules]
 */
class AllModulesReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The number of the modules that follow.
     */
    var modules by useField(JDWPInt())
    var modulesElements by useField(JDWPExternalVector(this::modules, ::AllModulesModulesElement))
}


class AllModulesModulesElement : JDWPComposite() {
    /**
     * One of the modules.
     */
    var module by useField(JDWPModuleId())
}

