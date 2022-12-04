import kotlin.test.assertEquals
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class RucksackReorganizationTest {
    companion object {
        private const val SAMPLE_FILE = "day3-input-sample.txt"
        private const val TEST_FILE = "day3-input-1.txt"

        @JvmStatic
        fun firstParams() = listOf(
            Arguments.of(SAMPLE_FILE, 157),
            Arguments.of(TEST_FILE, 8085),
        )

        @JvmStatic
        fun secondParams() = listOf(
            Arguments.of(SAMPLE_FILE, 70),
            Arguments.of(TEST_FILE, 209603),
        )
    }

    @ParameterizedTest
    @MethodSource("firstParams")
    fun `testing part1 question`(fileName: String, expected: Long) {
        val rucksacks = template(fileName)
        assertEquals(expected, Day3RucksackReorganization().part1(rucksacks).toLong())
    }

    @ParameterizedTest
    @MethodSource("secondParams")
    fun `testing part2 question`(fileName: String, expected: Long) {
        val rucksacks = template(fileName)
        assertEquals(expected, Day3RucksackReorganization().part2(rucksacks).toLong())
    }

    private fun template(fileName: String): MutableList<String> {
        val list = mutableListOf<String>()
        readFileWithNewLineFromResources(fileName, list::add)
        return list
    }
}