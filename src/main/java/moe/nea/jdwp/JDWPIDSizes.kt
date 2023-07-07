package moe.nea.jdwp

class JDWPIDSizes {
    companion object {
        fun standardSizes() = JDWPIDSizes().also {
            it.fieldIdSize = 8
            it.methodIdSize = 8
            it.objectIdSize = 8
            it.referenceTypeIdSize = 8
            it.frameIdSize = 8
        }
    }

    var fieldIdSize: Int = 0
        get() {
            if (field == 0) error("Accessing uninitialized Field Id size despite")
            return field
        }
    var methodIdSize: Int = 0
        get() {
            if (field == 0) error("Accessing uninitialized Method Id size despite")
            return field
        }
    var objectIdSize: Int = 0
        get() {
            if (field == 0) error("Accessing uninitialized Object Id size despite")
            return field
        }
    var referenceTypeIdSize: Int = 0
        get() {
            if (field == 0) error("Accessing uninitialized Reference Type Id size despite")
            return field
        }
    var frameIdSize: Int = 0
        get() {
            if (field == 0) error("Accessing uninitialized Frame Id size despite")
            return field
        }

}