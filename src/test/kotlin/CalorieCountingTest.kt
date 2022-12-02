import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CalorieCountingTest {
    companion object {
        @JvmStatic
        fun firstParams() = listOf(
            Arguments.of("day1-input-sample.txt", 24000),
            Arguments.of("day1-input-1.txt", 71506),
        )

        @JvmStatic
        fun secondParams() = listOf(
            Arguments.of("day1-input-sample.txt", 45000),
            Arguments.of("day1-input-1.txt", 209603),
        )
    }

    @ParameterizedTest
    @MethodSource("firstParams")
    fun `testing part1 question`(fileName: String, expected: Long) {
        val calories = elvesCalories(fileName)
        assertEquals(expected, CalorieCounting().part1(calories))
    }

    @ParameterizedTest
    @MethodSource("secondParams")
    fun `testing part2 question`(fileName: String, expected: Long) {
        val calories = elvesCalories(fileName)
        assertEquals(expected, CalorieCounting().part2(calories))
    }

    private fun elvesCalories(fileName: String): MutableList<Long> {
        val calories = mutableListOf<Long>()
        var calorie = 0L

        readFileWithNewLineFromResources(fileName) {
            if (it.isNotEmpty()) {
                calorie += it.toLong()
                return@readFileWithNewLineFromResources
            }
            calories.add(calorie)
            calorie = 0
        }
        calories.add(calorie)
        return calories
    }
}