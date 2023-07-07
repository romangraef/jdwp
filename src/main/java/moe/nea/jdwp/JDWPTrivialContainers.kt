package moe.nea.jdwp

interface JDWPSingleContainer<T : Any> : JDWPElement {
    var value: T?
}

abstract class JDWPSingleState<T : Any> : JDWPSingleContainer<T> {
    override var value: T? = null
    override fun toString(): String {
        return "${this::javaClass}($value)"
    }
}