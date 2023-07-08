package moe.nea.jdwp.struct.virtualmachine

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Retrieve the classpath and bootclasspath of the target VM. If the classpath is not defined, returns an empty list. If the bootclasspath is not defined returns an empty list.
 */
class ClassPaths : JDWPComposite(), JDWPCommandPayload<ClassPathsReply> {
    override val reply = ClassPathsReply()
    override val commandId: UByte get() = 13.toUByte()
    override val commandSetId: UByte get() = 1.toUByte()
}
/**
 * Reply for [ClassPaths]
 */
class ClassPathsReply : JDWPComposite(), JDWPReplyPayload {
    /**
     * Base directory used to resolve relative paths in either of the following lists.
     */
    var baseDir by useField(JDWPString())
    /**
     * Number of paths in classpath.
     */
    var classpaths by useField(JDWPInt())
    var classpathsElements by useField(JDWPExternalVector(this::classpaths, ::ClassPathsClasspathsElement))
    /**
     * Number of paths in bootclasspath.
     */
    var bootclasspaths by useField(JDWPInt())
    var bootclasspathsElements by useField(JDWPExternalVector(this::bootclasspaths, ::ClassPathsBootclasspathsElement))
}



class ClassPathsClasspathsElement : JDWPComposite() {
    /**
     * One component of classpath
     */
    var path by useField(JDWPString())
}



class ClassPathsBootclasspathsElement : JDWPComposite() {
    /**
     * One component of bootclasspath
     */
    var path by useField(JDWPString())
}

