class Day10CathodeRayTube {
    fun part1(commands: List<String>): Long {
        val strength = setOf(20, 60, 100, 140, 180, 220)
        var cycle = 1
        var sum = 0L
        var register = 1L
        commands.map { it.split(" ") }.forEach {
            val command = it[0]
            if (command == "noop") {
                cycle++
            } else if (command == "addx") {
                cycle++
                if (strength.contains(cycle)) sum += calculate(cycle, register)
                cycle++
                val num = it[1].toLong()
                register += num
            }
            if (strength.contains(cycle)) sum += calculate(cycle, register)
        }
        return sum
    }

    private fun calculate(cycle: Int, register: Long) = cycle * register

    fun part2(commands: List<String>): String {
        val sb = StringBuilder()
        var spritePosition = 0..2
        var position = 0
        commands.map { it.split(" ") }.forEach {
            when (it[0]) {
                "noop" -> {
                    val hasOverlapped = hasOverlapped(position, spritePosition)
                    sb.append(if (hasOverlapped) "#" else ".")
                }

                "addx" -> {
                    var hasOverlapped = hasOverlapped(position, spritePosition)
                    sb.append(if (hasOverlapped) "#" else ".")
                    position = (position + 1) % 40
                    if (position == 0) sb.append('\n')
                    val num = it[1].toInt()
                    hasOverlapped = hasOverlapped(position, spritePosition)
                    sb.append(if (hasOverlapped) "#" else ".")
                    spritePosition = shiftRange(spritePosition, num)
                }
            }
            position = (position + 1) % 40
            if (position == 0) sb.append('\n')
        }
        return sb.deleteAt(sb.length - 1).toString()
    }

    private fun hasOverlapped(position: Int, range: IntRange) = position in range

    private fun shiftRange(range: IntRange, shiftNumber: Int): IntRange {
        return range.first + shiftNumber..range.last + shiftNumber
    }
}