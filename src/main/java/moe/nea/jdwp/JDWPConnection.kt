package moe.nea.jdwp

class JDWPConnection private constructor(
    val reader: JDWPReader,
    val writer: JDWPWriter,
    val packetStore: JDWPPacketStore
) {
    companion object {
        /**
         * Create a JDWP connection from an initialized reader and writer (meaning the handshake was already sent)
         */
        fun fromInitialized(reader: JDWPReader, writer: JDWPWriter, packetStore: JDWPPacketStore) =
            JDWPConnection(reader, writer, packetStore)
    }

    fun setSizes(sizes: JDWPIDSizes) {
        reader.sizes.setFrom(sizes)
        writer.sizes.setFrom(sizes)
    }



}