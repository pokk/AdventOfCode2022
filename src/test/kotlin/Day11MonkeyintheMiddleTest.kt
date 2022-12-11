import java.util.LinkedList
import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day11MonkeyintheMiddleTest {
    companion object {
        private const val SAMPLE_FILE = "day11-input-sample.txt"
        private const val TEST_FILE = "day11-input-1.txt"

        @JvmStatic
        fun firstParams() = listOf(
            Arguments.of(SAMPLE_FILE, 10605),
            Arguments.of(TEST_FILE, 55458),
        )

        @JvmStatic
        fun secondParams() = listOf(
            Arguments.of(SAMPLE_FILE, 2713310158L),
            Arguments.of(TEST_FILE, 14508081294L),
        )
    }

    @ParameterizedTest
    @MethodSource("firstParams")
    fun `testing part1 question`(fileName: String, expected: Long) {
        val list = parse(fileName)
        assertEquals(expected, Day11MonkeyintheMiddle().part1(list))
    }

    @ParameterizedTest
    @MethodSource("secondParams")
    fun `testing part2 question`(fileName: String, expected: Long) {
        val list = parse(fileName)
        assertEquals(expected, Day11MonkeyintheMiddle().part2(list))
    }

    private fun parse(fileName: String): MutableList<Day11MonkeyintheMiddle.Monkey> {
        val list = mutableListOf<Day11MonkeyintheMiddle.Monkey>()
        val reader = javaClass.classLoader
            ?.getResourceAsStream(fileName)
            ?.bufferedReader()
        while (true) {
            repeat(6) {
                val str = reader?.readLine().orEmpty()
                val strs = str.split(" ").orEmpty()
                when {
                    strs[0] == "Monkey" -> list.add(Day11MonkeyintheMiddle.Monkey())
                    strs.any { it == "Starting" } -> {
                        list[list.lastIndex].items = LinkedList()
                        str.substring(18).replace(" ", "").split(",").map { it.toInt() }.forEach {
                            list[list.lastIndex].items.add(it.toLong())
                        }
                    }
                    strs.any { it == "Operation:" } -> {
                        list[list.lastIndex].newNum =
                            if (strs[strs.lastIndex] == "old") Int.MAX_VALUE else strs[strs.lastIndex].toInt()
                        list[list.lastIndex].op = strs[strs.size - 2]
                    }
                    strs.any { it == "Test:" } -> list[list.lastIndex].testDivisibleNum = strs[strs.lastIndex].toInt()
                    strs.any { it == "true:" } -> list[list.lastIndex].toMonkeyIfSuccess = strs[strs.lastIndex].toInt()
                    strs.any { it == "false:" } -> list[list.lastIndex].toMonkeyIfFailure = strs[strs.lastIndex].toInt()
                }
            }
            if (reader?.readLine() == null) break
        }
        return list
    }
}