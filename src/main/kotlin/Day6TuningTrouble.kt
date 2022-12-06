class Day6TuningTrouble {
    fun part1(datastream: CharArray): Int {
        var left = 0
        var right = 0
        val counting = IntArray(26)
        while (right < datastream.size) {
            val c = datastream[right]
            counting[c.code - 'a'.code]++
            if (right - left == 3 && counting[c.code - 'a'.code] == 1) return right + 1
            while (left < right && counting[c.code - 'a'.code] > 1) {
                counting[datastream[left++].code - 'a'.code]--
            }
            right++
        }
        return -1
    }

    fun part2(datastream: CharArray): Int {
        var left = 0
        var right = 0
        val counting = IntArray(26)
        while (right < datastream.size) {
            val c = datastream[right]
            counting[c.code - 'a'.code]++
            if (right - left == 13 && counting[c.code - 'a'.code] == 1) return right + 1
            while (left < right && counting[c.code - 'a'.code] > 1) {
                counting[datastream[left++].code - 'a'.code]--
            }

            right++
        }
        return -1
    }
}