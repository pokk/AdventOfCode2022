import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day8TreetopTreeHouseTest {
    companion object {
        private const val SAMPLE_FILE = "day8-input-sample.txt"
        private const val TEST_FILE = "day8-input-1.txt"

        @JvmStatic
        fun firstParams() = listOf(
            Arguments.of(SAMPLE_FILE, 21),
            Arguments.of(TEST_FILE, 1715),
        )

        @JvmStatic
        fun secondParams() = listOf(
            Arguments.of(SAMPLE_FILE, 8),
            Arguments.of(TEST_FILE, 209603),
        )
    }

    @ParameterizedTest
    @MethodSource("firstParams")
    fun `testing part1 question`(fileName: String, expected: Long) {
        val list = parse(fileName)
        assertEquals(expected, Day8TreetopTreeHouse().part1(list))
    }

    @ParameterizedTest
    @MethodSource("secondParams")
    fun `testing part2 question`(fileName: String, expected: Long) {
        val list = parse(fileName)
        assertEquals(expected, Day8TreetopTreeHouse().part2(list))
    }

    private fun parse(fileName: String): MutableList<String> {
        val list = mutableListOf<String>()
        readFileWithNewLineFromResources(fileName, list::add)
        return list
    }
}