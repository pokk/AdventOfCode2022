import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day2RockPaperScissorsTest {
    companion object {
        private const val SAMPLE_FILE = "day2-input-sample.txt"
        private const val TEST_FILE = "day2-input-1.txt"

        @JvmStatic
        fun firstParams() = listOf(
            Arguments.of(SAMPLE_FILE, 15),
            Arguments.of(TEST_FILE, 12276),
        )

        @JvmStatic
        fun secondParams() = listOf(
            Arguments.of(SAMPLE_FILE, 12),
            Arguments.of(TEST_FILE, 9975),
        )
    }

    @ParameterizedTest
    @MethodSource("firstParams")
    fun `testing part1 question`(fileName: String, expected: Long) {
        val list = goEachRound(fileName)
        assertEquals(expected, Day2RockPaperScissors().part1(list[0], list[1]))
    }

    @ParameterizedTest
    @MethodSource("secondParams")
    fun `testing part2 question`(fileName: String, expected: Long) {
        val list = goEachRound(fileName)
        assertEquals(expected, Day2RockPaperScissors().part2(list[0], list[1]))
    }

    private fun goEachRound(fileName: String): List<List<Char>> {
        val list = listOf(mutableListOf<Char>(), mutableListOf())
        readFileWithNewLineFromResources(fileName) {
            val strs = it.toCharArray()
            list[0].add(strs[0])
            list[1].add(strs[2])
        }
        return list
    }
}