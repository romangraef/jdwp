package moe.nea.jdwp.struct.event

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import moe.nea.jdwp.struct.base.*

/**
 * Several events may occur at a given time in the target VM. For example, there may be more than one breakpoint request for a given location or you might single step to the same location as a breakpoint request.  These events are delivered together as a composite event.  For uniformity, a composite event is always used to deliver events, even if there is only one event to report. 
 * The invoke options are a combination of zero or more of the following bit flags:
 */
/**
 * Reply for [Composite]
 */
