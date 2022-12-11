import java.util.LinkedList
import java.util.Queue

class Day11MonkeyintheMiddle {
    data class Monkey(
        var items: Queue<Long> = LinkedList(),
        var op: String = "",
        var newNum: Int = 0,
        var testDivisibleNum: Int = -1,
        var toMonkeyIfSuccess: Int = -1,
        var toMonkeyIfFailure: Int = -1,
    ) {
        fun throwToWhichMonkeyWithNewWorry(divided: Int = 1, mod: Long = 1L): Pair<Int, Long> {
            val item = items.remove()
            val newWorry = if (divided != 1) {
                worryLevel(item) / divided
            } else {
                worryLevel(item) % mod
            }
            val testResult = newWorry % testDivisibleNum == 0L
            val which = if (testResult) toMonkeyIfSuccess else toMonkeyIfFailure
            return which to newWorry
        }

        private fun worryLevel(oldWorry: Long): Long {
            val level = if (newNum == Int.MAX_VALUE) oldWorry else newNum.toLong()
            return when (op) {
                "+" -> oldWorry + level
                "*" -> oldWorry * level
                else -> throw UnsupportedOperationException()
            }
        }
    }

    fun part1(monkeys: List<Monkey>): Long {
        val counting = MutableList(monkeys.size) { 0L }

        repeat(20) {
            monkeys.forEachIndexed { index, monkey ->
                counting[index] += monkey.items.size.toLong()
                repeat(monkey.items.size) {
                    val (which, worry) = monkey.throwToWhichMonkeyWithNewWorry(3)
                    monkeys[which].items.add(worry)
                }
            }
        }

        return counting.sorted().reversed().take(2).reduce { acc, l -> acc * l }
    }

    fun part2(monkeys: List<Monkey>): Long {
        val counting = MutableList(monkeys.size) { 0L }
        val mod = monkeys.map(Monkey::testDivisibleNum).reduce { acc, i -> acc * i }.toLong()

        repeat(10000) {
            monkeys.forEachIndexed { index, monkey ->
                counting[index] += monkey.items.size.toLong()
                repeat(monkey.items.size) {
                    val (which, worry) = monkey.throwToWhichMonkeyWithNewWorry(mod = mod)
                    monkeys[which].items.add(worry)
                }
            }
        }

        return counting.sorted().reversed().take(2).reduce { acc, l -> acc * l }
    }
}