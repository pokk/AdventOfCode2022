class Day9RopeBridge {
    companion object {
        val dirs = listOf(
            listOf(1, 0),
            listOf(0, 1),
            listOf(-1, 0),
            listOf(0, -1),
            listOf(0, 0),
            listOf(1, 1),
            listOf(-1, 1),
            listOf(1, -1),
            listOf(-1, -1)
        )
        val dirMap = mapOf(
            "U" to dirs[0],
            "R" to dirs[1],
            "D" to dirs[2],
            "L" to dirs[3],
        )
    }

    data class Position(
        var x: Int,
        var y: Int,
    ) {
        fun inAround(another: Position): Boolean {
            return dirs.any { (xStep, yStep) ->
                another.x == x + xStep && another.y == y + yStep
            }
        }

        fun move(dir: List<Int>) {
            val (xStep, yStep) = dir
            x += xStep
            y += yStep
        }
    }

    fun part1(commands: List<String>): Long {
        val visited = mutableMapOf<String, Int>()
        val head = Position(0, 0)
        val tail = Position(0, 0)
        visited[tail.toString()] = 1
        commands.map { it.split(" ") }
            .forEach { (direction, steps) ->
                val go = requireNotNull(dirMap[direction])
                repeat(steps.toInt()) {
                    head.move(go)
                    if (!head.inAround(tail)) {
                        when (direction) {
                            "D" -> {
                                tail.x = head.x + 1
                                tail.y = head.y
                            }

                            "R" -> {
                                tail.x = head.x
                                tail.y = head.y - 1
                            }

                            "U" -> {
                                tail.x = head.x - 1
                                tail.y = head.y
                            }

                            "L" -> {
                                tail.x = head.x
                                tail.y = head.y + 1
                            }
                        }
                        visited[tail.toString()] = visited.getOrDefault(tail.toString(), 0) + 1
                    }
                }
            }
        return visited.values.size.toLong()
    }

    fun part2(commands: List<String>): Long {
        TODO()
    }
}