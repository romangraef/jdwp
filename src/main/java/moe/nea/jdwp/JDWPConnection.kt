package moe.nea.jdwp

class JDWPConnection private constructor(val reader: JDWPReader, val writer: JDWPWriter) {
    companion object {
        /**
         * Create a JDWP connection from an initialized reader and writer (meaning the handshake was already sent)
         */
        fun fromInitialized(reader: JDWPReader, writer: JDWPWriter) = JDWPConnection(reader, writer)
    }

    fun setSizes(sizes: JDWPIDSizes) {
        reader.sizes.setFrom(sizes)
        writer.sizes.setFrom(sizes)
    }

}