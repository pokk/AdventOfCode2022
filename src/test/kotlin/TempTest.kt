import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class TempTest {
    companion object {
        private const val SAMPLE_FILE = "day1-input-sample.txt"
        private const val TEST_FILE = "day1-input-1.txt"

        @JvmStatic
        fun firstParams() = listOf(
            Arguments.of(SAMPLE_FILE, 24000),
            Arguments.of(TEST_FILE, 71506),
        )

        @JvmStatic
        fun secondParams() = listOf(
            Arguments.of(SAMPLE_FILE, 45000),
            Arguments.of(TEST_FILE, 209603),
        )
    }

    @ParameterizedTest
    @MethodSource("firstParams")
    fun `testing part1 question`(fileName: String, expected: Long) {
        val calories = template(fileName)
        assertEquals(expected, TODO())
    }

    @ParameterizedTest
    @MethodSource("secondParams")
    fun `testing part2 question`(fileName: String, expected: Long) {
        val calories = template(fileName)
        assertEquals(expected, TODO())
    }

    private fun template(fileName: String): MutableList<Long> {
        val list = mutableListOf<Long>()
        readFileWithNewLineFromResources(fileName) {
        }
        return list
    }
}