import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day10CathodeRayTubeTest {
    companion object {
        private const val SAMPLE_FILE = "day10-input-sample.txt"
        private const val TEST_FILE = "day10-input-1.txt"

        @JvmStatic
        fun firstParams() = listOf(
            Arguments.of(SAMPLE_FILE, 13140),
            Arguments.of(TEST_FILE, 6269),
        )

        @JvmStatic
        fun secondParams() = listOf(
            Arguments.of(
                SAMPLE_FILE, """##..##..##..##..##..##..##..##..##..##..
###...###...###...###...###...###...###.
####....####....####....####....####....
#####.....#####.....#####.....#####.....
######......######......######......####
#######.......#######.......#######.....""".intern()
            ),
            Arguments.of(
                TEST_FILE, """####.#..#.###..####.###....##..##..#....
#....#..#.#..#....#.#..#....#.#..#.#....
###..####.#..#...#..#..#....#.#....#....
#....#..#.###...#...###.....#.#.##.#....
#....#..#.#....#....#....#..#.#..#.#....
####.#..#.#....####.#.....##...###.####."""
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("firstParams")
    fun `testing part1 question`(fileName: String, expected: Long) {
        val list = parse(fileName)
        assertEquals(expected, Day10CathodeRayTube().part1(list))
    }

    @ParameterizedTest
    @MethodSource("secondParams")
    fun `testing part2 question`(fileName: String, expected: String) {
        val list = parse(fileName)
        assertEquals(expected, Day10CathodeRayTube().part2(list))
    }

    private fun parse(fileName: String): MutableList<String> {
        val list = mutableListOf<String>()
        readFileWithNewLineFromResources(fileName, list::add)
        return list
    }
}