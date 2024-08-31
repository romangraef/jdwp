package moe.nea.jdwp.base

import moe.nea.jdwp.primitives.ByteTagged

enum class JDWPEventKindConstants(override val tag: UByte) : ByteTagged {
	SINGLE_STEP(1.toUByte()),
	BREAKPOINT(2.toUByte()),
	FRAME_POP(3.toUByte()),
	EXCEPTION(4.toUByte()),
	USER_DEFINED(5.toUByte()),
	THREAD_START(6.toUByte()),
	THREAD_DEATH(7.toUByte()),
	THREAD_END(7.toUByte()),
	CLASS_PREPARE(8.toUByte()),
	CLASS_UNLOAD(9.toUByte()),
	CLASS_LOAD(10.toUByte()),
	FIELD_ACCESS(20.toUByte()),
	FIELD_MODIFICATION(21.toUByte()),
	EXCEPTION_CATCH(30.toUByte()),
	METHOD_ENTRY(40.toUByte()),
	METHOD_EXIT(41.toUByte()),
	METHOD_EXIT_WITH_RETURN_VALUE(42.toUByte()),
	MONITOR_CONTENDED_ENTER(43.toUByte()),
	MONITOR_CONTENDED_ENTERED(44.toUByte()),
	MONITOR_WAIT(45.toUByte()),
	MONITOR_WAITED(46.toUByte()),
	VM_START(90.toUByte()),
	VM_INIT(90.toUByte()),
	VM_DEATH(99.toUByte()),
	VM_DISCONNECTED(100.toUByte()),}