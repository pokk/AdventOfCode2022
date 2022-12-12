import java.util.PriorityQueue

class Day12HillClimbingAlgorithm {
    data class Position(
        val x: Int,
        val y: Int,
    ) {
        var steps: Int = 0

        fun move(move: Move) = Position(x + move.x, y + move.y).apply {
            this.steps = this@Position.steps + 1
        }
    }

    data class Move(
        val x: Int,
        val y: Int,
    )

    private val dirs = listOf(Move(0, 1), Move(1, 0), Move(0, -1), Move(-1, 0))

    fun part1(mazeStr: List<String>): Long {
        val (maze, start, end) = generateMaze(mazeStr)
        val queue = PriorityQueue<Position> { a, b -> a.steps - b.steps }
        val visited = mutableSetOf<String>()
        queue.add(start)
        visited.add(start.toString())

        while (queue.isNotEmpty()) {
            val p = queue.remove()
            for (dir in dirs) {
                val newPos = p.move(dir)
                if (newPos.x !in 0..maze.lastIndex || newPos.y !in 0..maze.first().lastIndex) continue
                if (visited.contains(newPos.toString())) continue
                if (maze[newPos.x][newPos.y] - maze[p.x][p.y] > 1) continue
                if (maze[newPos.x][newPos.y] == 'z' && newPos == end) return newPos.steps.toLong()
                visited.add(newPos.toString())
                queue.add(newPos)
            }
        }
        return -1L
    }

    private fun generateMaze(mazeStr: List<String>): Triple<List<MutableList<Char>>, Position, Position> {
        val maze = mazeStr.map { it.toCharArray().toMutableList() }
        var start = Position(0, 0)
        var end = Position(0, 0)
        maze.forEachIndexed outter@{ i, row ->
            row.forEachIndexed { j, c ->
                if (c == 'S') {
                    start = Position(i, j)
                    maze[i][j] = 'a'
                }
                if (c == 'E') {
                    end = Position(i, j)
                    maze[i][j] = 'z'
                }
            }
        }
        return Triple(maze, start, end)
    }

    fun part2(mazeStr: List<String>): Long {
        val (maze, start, end) = generateMaze(mazeStr)
        val queue = PriorityQueue<Position> { a, b -> a.steps - b.steps }
        val visited = mutableSetOf<String>()
        queue.add(end)
        visited.add(end.toString())

        while (queue.isNotEmpty()) {
            val p = queue.remove()
            for (dir in dirs) {
                val newPos = p.move(dir)
                if (newPos.x !in 0..maze.lastIndex || newPos.y !in 0..maze.first().lastIndex) continue
                if (visited.contains(newPos.toString())) continue
                if (maze[newPos.x][newPos.y] - maze[p.x][p.y] < -1) continue
                if (maze[newPos.x][newPos.y] == 'a') return newPos.steps.toLong()
                visited.add(newPos.toString())
                queue.add(newPos)
            }
        }
        return -1L
    }
}