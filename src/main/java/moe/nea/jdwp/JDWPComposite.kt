package moe.nea.jdwp

import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class JDWPComposite : JDWPElement {
    private val _fields: MutableList<JDWPElement> = mutableListOf()
    val allFields: List<JDWPElement> get() = _fields
    protected fun <T : JDWPElement> useField(primitive: T): ReadOnlyProperty<JDWPComposite, T> {
        _fields.add(primitive)
        return ReadOnlyProperty { _, _ -> primitive }
    }

    protected fun <T : JDWPSingleContainer<V>, V : Any> useField(primitive: T): ReadWriteProperty<JDWPComposite, V> {
        _fields.add(primitive)
        return object : ReadWriteProperty<JDWPComposite, V> {
            override fun getValue(thisRef: JDWPComposite, property: KProperty<*>): V {
                return primitive.value!!
            }

            override fun setValue(thisRef: JDWPComposite, property: KProperty<*>, value: V) {
                primitive.value = value
            }
        }
    }

    override fun read(reader: JDWPReader) {
        _fields.forEach { it.read(reader) }
    }

    override fun write(writer: JDWPWriter) {
        _fields.forEach { it.write(writer) }
    }

    override fun toString(): String {
        return "${this.javaClass.simpleName}{${_fields.joinToString(";")}}"
    }
}