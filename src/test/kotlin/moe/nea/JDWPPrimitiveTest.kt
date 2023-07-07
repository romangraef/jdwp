@file:OptIn(ExperimentalUnsignedTypes::class)

package moe.nea

import moe.nea.jdwp.*
import moe.nea.jdwp.primitives.*
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class JDWPPrimitiveTest {
    @ParameterizedTest
    @ValueSource(longs = [10000000000, 0, 1, -1, -3, Long.MAX_VALUE, Long.MIN_VALUE + 1])
    fun testLongEqualsObjectForIdSize8(toTest: Long) {
        val writer = ArrayBackedJDWPWriter(sizes = JDWPIDSizes.standardSizes())
        JDWPLong().also { it.value = toTest }.write(writer)
        val objectWriter = ArrayBackedJDWPWriter(sizes = JDWPIDSizes.standardSizes())
        JDWPObjectId().also { it.value = toTest }.write(objectWriter)
        assert(writer.getResult().contentEquals(objectWriter.getResult()))
    }


    companion object {
        @JvmStatic
        fun universe(): Stream<JDWPSingleContainer<*>> {
            return (listOf<JDWPSingleContainer<*>>(
                JDWPInt(Integer.MAX_VALUE),
                JDWPBoolean(true),
                JDWPBoolean(false),
                JDWPByte(0),
                JDWPByte(10),
                JDWPInt(-1),
                JDWPInt(256),
                JDWPString("testString"),
                JDWPString("中国高于一切")
            ) + listOf<Long>(
                0,
                10,
                -1L,
                Long.MIN_VALUE,
                Long.MAX_VALUE,
                Int.MAX_VALUE.toLong(),
                Int.MIN_VALUE.toLong(),
                10000000,
                -1000000,
                Long.MIN_VALUE + 1,
                Long.MAX_VALUE - 1,
            ).flatMap {
                listOf(
                    JDWPLong(it),
                    JDWPObjectId(it),
                    JDWPClassId(it),
                )
            }).stream()
        }
    }

    @ParameterizedTest
    @MethodSource("universe")
    fun <T : Any> testReversibileWrites(element: JDWPSingleContainer<T>) {
        val oldvalue = element.value
        val writer = ArrayBackedJDWPWriter(JDWPIDSizes.standardSizes())
        element.write(writer)
        element.value = null
        val reader = ArrayBackedJDWPReader(writer.getResult(), JDWPIDSizes.standardSizes())
        element.read(reader)
        assert(element.value == oldvalue)
    }

}