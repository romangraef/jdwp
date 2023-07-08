package moe.nea.jdwp.struct.classloaderreference

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Returns a list of all classes which this class loader can find by name via ,  and bytecode linkage. That is, all classes for which this class loader has been recorded as an  loader. The list contains each reference type created by this loader and any types for which loading was delegated by this class loader to another class loader.
 */
class VisibleClasses : JDWPComposite(), JDWPCommandPayload<VisibleClassesReply> {
    /**
     * The class loader object ID.
     */
    var classLoaderObject by useField(JDWPClassLoaderId())
    override val reply = VisibleClassesReply()
    override val commandId: UByte get() = 1.toUByte()
    override val commandSetId: UByte get() = 14.toUByte()
}
/**
 * Reply for [VisibleClasses]
 */
class VisibleClassesReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * The number of visible classes.
     */
    var classes by useField(JDWPInt())
    var classesElements by useField(JDWPExternalVector(this::classes, ::VisibleClassesClassesElement))
}



class VisibleClassesClassesElement : JDWPComposite() {
    /**
     * Kind of following reference type.
     */
    var refTypeTag by useField(JDWPByte())
    /**
     * A class visible to this class loader.
     */
    var typeID by useField(JDWPReferenceTypeId())
}


