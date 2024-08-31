package moe.nea.jdwp.primitives

import moe.nea.jdwp.JDWPElement
import moe.nea.jdwp.JDWPReader
import moe.nea.jdwp.JDWPSingleContainer
import moe.nea.jdwp.JDWPWriter
import kotlin.reflect.KMutableProperty0

// TODO: all known cases of conditionals are bytes. i dont know if there are docs on this being consistent
class JDWPCase<T : JDWPElement>(
	private val conditionGetter: () -> UByte,
	private val conditionSetter: (UByte) -> Unit,
	private val conditionValue: UByte,
	private var _contents: T,
) : JDWPSingleContainer<T> {

	companion object {
		operator fun <T : JDWPElement> invoke(
			conditionVariable: KMutableProperty0<UByte>,
			conditionValue: UByte,
			contents: T
		): JDWPCase<T> {
			return JDWPCase(conditionVariable, conditionVariable::set, conditionValue, contents)
		}

		operator fun <T : JDWPElement> invoke(
			conditionVariable: KMutableProperty0<UByte>,
			conditionValue: ByteTagged,
			contents: T
		): JDWPCase<T> {
			return invoke(conditionVariable, conditionValue.tag, contents)
		}
	}

	fun isSelected() = conditionGetter.invoke() == conditionValue

	override var value: T?
		get() = if (!isSelected()) null else _contents
		set(value) {
			conditionSetter.invoke(conditionValue)
			_contents = value
				?: error("Setting a case to null is not allowed. Instead set another case and this one will be nulled automatically")
		}


	override fun read(reader: JDWPReader) {
		if (isSelected())
			_contents.read(reader)
	}

	override fun write(writer: JDWPWriter) {
		if (isSelected())
			_contents.write(writer)
	}

	override fun toString(): String {
		return "JDWPCase(${isSelected()};$value}"
	}
}