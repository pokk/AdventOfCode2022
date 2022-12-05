import java.util.Stack

class Day5SupplyStacks {
    data class Instruction(
        val quantity: Int,
        val from: Int,
        val to: Int,
    )

    fun part1(crates: List<Stack<Char>>, instructions: List<Instruction>): String {
        instructions.forEach {
            val fromStack = crates[it.from - 1]
            val toStack = crates[it.to - 1]
            repeat(it.quantity) { toStack.push(fromStack.pop()) }
        }
        return buildString { crates.forEach { append(it.peek()) } }
    }

    fun part2(crates: List<Stack<Char>>, instructions: List<Instruction>): String {
        val stack = Stack<Char>()
        instructions.forEach {
            val fromStack = crates[it.from - 1]
            val toStack = crates[it.to - 1]
            repeat(it.quantity) { stack.push(fromStack.pop()) }
            while (stack.isNotEmpty()) toStack.push(stack.pop())
        }
        return buildString { crates.forEach { append(it.peek()) } }
    }
}