import java.util.LinkedList
import java.util.Stack
import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day5SupplyStacksTest {
    companion object {
        private const val SAMPLE_FILE = "day5-input-sample.txt"
        private const val TEST_FILE = "day5-input-1.txt"

        @JvmStatic
        fun firstParams() = listOf(
            Arguments.of(SAMPLE_FILE, "CMZ"),
            Arguments.of(TEST_FILE, "QMBMJDFTD"),
        )

        @JvmStatic
        fun secondParams() = listOf(
            Arguments.of(SAMPLE_FILE, "MCD"),
            Arguments.of(TEST_FILE, "NBTVTJNFJ"),
        )
    }

    @ParameterizedTest
    @MethodSource("firstParams")
    fun `testing part1 question`(fileName: String, expected: String) {
        val list = parseFile(fileName)
        assertEquals(expected, Day5SupplyStacks().part1(list.first, list.second))
    }

    @ParameterizedTest
    @MethodSource("secondParams")
    fun `testing part2 question`(fileName: String, expected: String) {
        val list = parseFile(fileName)
        assertEquals(expected, Day5SupplyStacks().part2(list.first, list.second))
    }

    private fun parseFile(fileName: String): Pair<List<Stack<Char>>, List<Day5SupplyStacks.Instruction>> {
        val list = mutableListOf<Stack<Char>>()
        val instructions = mutableListOf<Day5SupplyStacks.Instruction>()
        var isReadingGraphPart = true
        readFileWithNewLineFromResources(fileName) {
            if (it.isEmpty()) {
                isReadingGraphPart = false
                return@readFileWithNewLineFromResources
            }

            if (isReadingGraphPart) {
                readStackAndKeep(it, list)
            } else {
                instructions.add(parseInstruction(it))
            }
        }
        val queue = LinkedList<Char>()
        list.forEach {
            while (it.isNotEmpty()) queue.add(it.pop())
            while (queue.isNotEmpty()) it.push(queue.remove())
        }
        return list to instructions
    }

    private fun readStackAndKeep(line: String, list: MutableList<Stack<Char>>) {
        line.toCharArray().forEachIndexed { index, c ->
            if (!Character.isAlphabetic(c.code)) return@forEachIndexed
            val pos = index / 4
            fillUpList(pos + 1, list)
            list[pos].push(c)
        }
    }

    private fun fillUpList(size: Int, list: MutableList<Stack<Char>>) {
        while (size > list.size) {
            list.add(Stack())
        }
    }

    private fun parseInstruction(line: String): Day5SupplyStacks.Instruction {
        val strs = line.split(" ")
        return Day5SupplyStacks.Instruction(
            strs[1].toInt(),
            strs[3].toInt(),
            strs[5].toInt(),
        )
    }
}