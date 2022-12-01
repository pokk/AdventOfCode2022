import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CalorieCountingTest {
    companion object {
        @JvmStatic
        fun files() = listOf(
            Arguments.of("day1-input-1.txt"),
        )
    }

    @ParameterizedTest
    @MethodSource("files")
    fun `testing simple input`(fileName: String) {
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

        println(CalorieCounting().solution(calories))
    }
}