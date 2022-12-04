class Day3RucksackReorganization {
    fun part1(rucksacks: List<String>): Int {
        return rucksacks.map { calculate(it.toCharArray()) }.sumOf(::getScore)
    }

    private fun calculate(rucksack: CharArray): Char {
        var left = 0
        var right = rucksack.size - 1
        val firstSet = mutableSetOf<Char>()
        val secondSet = mutableSetOf<Char>()
        while (left < right) {
            firstSet.add(rucksack[left++])
            secondSet.add((rucksack[right--]))
        }
        val overlapped = firstSet.intersect(secondSet)
        return overlapped.first()
    }

    fun part2(rucksacks: List<String>): Int {
        return rucksacks.chunked(3).sumOf {
            val char = it.map { appearChar(it.toCharArray()) }.reduce(Set<Char>::intersect).first()
            getScore(char)
        }
    }

    private fun appearChar(str: CharArray): Set<Char> = buildSet {
        str.forEach(::add)
    }

    private fun getScore(c: Char) = when (c) {
        in 'a'..'z' -> c.code - 'a'.code + 1
        in 'A'..'Z' -> c.code - 'A'.code + 27
        else -> -1
    }
}