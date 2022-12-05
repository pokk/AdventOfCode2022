class Day4CampCleanup {
    fun part1(rows: List<String>): Int {
        return rows.map { it.split(",") }
            .map { hasWholeOverlapped(it[0], it[1]) }
            .map { if (it) 1 else 0 }
            .sum()
    }

    private fun hasWholeOverlapped(firstInterval: String, secondInterval: String): Boolean {
        val (firstStart, firstEnd) = firstInterval.split("-")
        val (secondStart, secondEnd) = secondInterval.split("-")
        val firstRange = firstStart.toInt()..firstEnd.toInt()
        val secondRange = secondStart.toInt()..secondEnd.toInt()
        return (firstStart.toInt() in secondRange && firstEnd.toInt() in secondRange) ||
            (secondStart.toInt() in firstRange && secondEnd.toInt() in firstRange)
    }

    fun part2(rows: List<String>): Int {
        return rows.map { it.split(",") }
            .map { hasOverlapped(it[0], it[1]) }
            .map { if (it) 1 else 0 }
            .sum()
    }

    private fun hasOverlapped(firstInterval: String, secondInterval: String): Boolean {
        val (firstStart, firstEnd) = firstInterval.split("-")
        val (secondStart, secondEnd) = secondInterval.split("-")
        val firstRange = firstStart.toInt()..firstEnd.toInt()
        val secondRange = secondStart.toInt()..secondEnd.toInt()
        return (firstStart.toInt() in secondRange || firstEnd.toInt() in secondRange) ||
            (secondStart.toInt() in firstRange || secondEnd.toInt() in firstRange)
    }
}