class Day2RockPaperScissors {
    enum class Comparison {
        Rock {
            override fun weakTo() = Paper
            override fun strongTo() = Scissors
            override fun point() = 1
        },
        Paper {
            override fun weakTo() = Scissors
            override fun strongTo() = Rock
            override fun point() = 2
        },
        Scissors {
            override fun weakTo() = Rock
            override fun strongTo() = Paper
            override fun point() = 3
        };

        abstract fun weakTo(): Comparison
        abstract fun strongTo(): Comparison
        abstract fun point(): Int

        fun compare(opponent: Comparison): Int {
            if (weakTo() == opponent) return -1
            if (strongTo() == opponent) return 1
            return 0
        }
    }

    fun part1(elf: List<Char>, player: List<Char>): Long {
        val mapping = buildMap {
            put('A', Comparison.Rock) // Rock
            put('B', Comparison.Paper) // Paper
            put('C', Comparison.Scissors) // Scissors
            put('X', Comparison.Rock) // Rock
            put('Y', Comparison.Paper) // Paper
            put('Z', Comparison.Scissors) // Scissors
        }

        var sum = 0L

        elf.zip(player).forEach {
            val a = requireNotNull(mapping[it.second])
            val b = requireNotNull(mapping[it.first])
            sum += when (a.compare(b)) {
                -1 -> 0
                1 -> 6
                else -> 3
            }
            sum += a.point()
        }

        return sum
    }

    fun part2(elf: List<Char>, player: List<Char>): Long {
        var sum = 0L

        val mapping = buildMap {
            put('A', Comparison.Rock) // Rock
            put('B', Comparison.Paper) // Paper
            put('C', Comparison.Scissors) // Scissors
        }
        val points = buildMap {
            put('X', 0) // Lose
            put('Y', 3) // Draw
            put('Z', 6) // Win
        }
        elf.zip(player).forEach {
            val a = requireNotNull(mapping[it.first])
            val point = requireNotNull(points[it.second])
            sum += point
            val another = when (point) {
                0 -> a.strongTo().point()
                3 -> a.point()
                6 -> a.weakTo().point()
                else -> 0
            }
            sum += another
        }

        return sum
    }
}