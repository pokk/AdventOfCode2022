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
                        tail.move(alignMove(tail, head))
                    }
                    visited[tail.toString()] = visited.getOrDefault(tail.toString(), 0) + 1
                }
            }
        return visited.values.size.toLong()
    }

    fun part2(commands: List<String>): Long {
        val visited = mutableSetOf<String>()
        val head = Position(0, 0)
        var bodies = mutableListOf(head)
        repeat(9) { bodies.add(Position(0, 0)) }
        visited.add(head.toString())
        commands.map { it.split(" ") }
            .forEach { (direction, steps) ->
                val go = requireNotNull(dirMap[direction])
                repeat((steps.toInt())) {
                    head.move(go)
                    for (index in 1..bodies.lastIndex) {
                        val prev = bodies[index - 1]
                        val body = bodies[index]
                        if (!prev.inAround(body))
                            body.move(alignMove(body, prev))
                    }
                    visited.add(bodies[bodies.size - 1].toString())
                }
            }

        return visited.size.toLong()
    }

    private fun alignMove(position: Position, nextPosition: Position): List<Int> {
        return listOf((nextPosition.x - position.x).coerceIn(-1, 1), (nextPosition.y - position.y).coerceIn(-1, 1))
    }
}