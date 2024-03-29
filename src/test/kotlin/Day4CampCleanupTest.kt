import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day4CampCleanupTest {
    companion object {
        private const val SAMPLE_FILE = "day4-input-sample.txt"
        private const val TEST_FILE = "day4-input-1.txt"

        @JvmStatic
        fun firstParams() = listOf(
            Arguments.of(SAMPLE_FILE, 2),
            Arguments.of(TEST_FILE, 462),
        )

        @JvmStatic
        fun secondParams() = listOf(
            Arguments.of(SAMPLE_FILE, 4),
            Arguments.of(TEST_FILE, 209603),
        )
    }

    @ParameterizedTest
    @MethodSource("firstParams")
    fun `testing part1 question`(fileName: String, expected: Int) {
        val list = template(fileName)
        assertEquals(expected, Day4CampCleanup().part1(list))
    }

    @ParameterizedTest
    @MethodSource("secondParams")
    fun `testing part2 question`(fileName: String, expected: Int) {
        val list = template(fileName)
        assertEquals(expected, Day4CampCleanup().part2(list))
    }

    private fun template(fileName: String): MutableList<String> {
        val list = mutableListOf<String>()
        readFileWithNewLineFromResources(fileName, list::add)
        return list
    }
}