import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day12HillClimbingAlgorithmTest {
    companion object {
        private const val SAMPLE_FILE = "day12-input-sample.txt"
        private const val TEST_FILE = "day12-input-1.txt"

        @JvmStatic
        fun firstParams() = listOf(
            Arguments.of(SAMPLE_FILE, 31),
            Arguments.of(TEST_FILE, 528),
        )

        @JvmStatic
        fun secondParams() = listOf(
            Arguments.of(SAMPLE_FILE, 29),
            Arguments.of(TEST_FILE, 522),
        )
    }

    @ParameterizedTest
    @MethodSource("firstParams")
    fun `testing part1 question`(fileName: String, expected: Long) {
        val list = parse(fileName)
        assertEquals(expected, Day12HillClimbingAlgorithm().part1(list))
    }

    @ParameterizedTest
    @MethodSource("secondParams")
    fun `testing part2 question`(fileName: String, expected: Long) {
        val list = parse(fileName)
        assertEquals(expected, Day12HillClimbingAlgorithm().part2(list))
    }

    private fun parse(fileName: String): MutableList<String> {
        val list = mutableListOf<String>()
        readFileWithNewLineFromResources(fileName, list::add)
        return list
    }
}