import java.util.Stack
import kotlin.math.max

class Day8TreetopTreeHouse {
    fun part1(commands: List<String>): Long {
        val map = makeMap(commands)
        val visibleMap = List(map.size) { List(map[0].size) { false }.toMutableList() }
        fromLeftSee(map, visibleMap)
        fromRightSee(map, visibleMap)
        fromTopSee(map, visibleMap)
        fromBottomSee(map, visibleMap)
        visibleMap[0].fill(true)
        visibleMap[visibleMap.lastIndex].fill(true)
        for (i in visibleMap.indices) {
            visibleMap[i][0] = true
            visibleMap[i][visibleMap.lastIndex] = true
        }

        return visibleMap.sumOf {
            it.sumOf { if (it) 1 else 0L }
        }
    }

    private fun makeMap(commands: List<String>) = buildList {
        commands.map(String::toCharArray)
            .forEach { add(buildList { it.forEach { add(it.code - '0'.code) } }) }
    }

    private fun fromLeftSee(map: List<List<Int>>, visibleMap: List<MutableList<Boolean>>) {
        for (i in map.indices) {
            var highest = map[i][0]
            for (j in map[i].indices) {
                if (map[i][j] > highest) {
                    visibleMap[i][j] = true
                    highest = map[i][j]
                }
            }
        }
    }

    private fun fromRightSee(map: List<List<Int>>, visibleMap: List<MutableList<Boolean>>) {
        for (i in map.indices) {
            var highest = map[i][map.lastIndex]
            for (j in map[i].lastIndex downTo 0) {
                if (map[i][j] > highest) {
                    visibleMap[i][j] = true
                    highest = map[i][j]
                }
            }
        }
    }

    private fun fromTopSee(map: List<List<Int>>, visibleMap: List<MutableList<Boolean>>) {
        for (i in map[0].indices) {
            var highest = map[0][i]
            for (j in map.indices) {
                if (map[j][i] > highest) {
                    visibleMap[j][i] = true
                    highest = map[j][i]
                }
            }
        }
    }

    private fun fromBottomSee(map: List<List<Int>>, visibleMap: List<MutableList<Boolean>>) {
        for (i in map[0].lastIndex downTo 0) {
            var highest = map[map.lastIndex][i]
            for (j in map.lastIndex downTo 0) {
                if (map[j][i] > highest) {
                    visibleMap[j][i] = true
                    highest = map[j][i]
                }
            }
        }
    }

    fun part2(commands: List<String>): Long {
        val map = makeMap(commands)
        val scoreMap = List(map.size) { List(map[0].size) { 1L }.toMutableList() }
        fromLeftCheck(map, scoreMap)
        fromRightCheck(map, scoreMap)
        fromTopCheck(map, scoreMap)
        fromBottomCheck(map, scoreMap)
        var score = 1L
        for (i in 1 until scoreMap.lastIndex) {
            for (j in 1 until scoreMap[i].lastIndex) {
                score = max(score, scoreMap[i][j])
            }
        }
        return score
    }

    private fun fromLeftCheck(map: List<List<Int>>, scoreMap: List<MutableList<Long>>) {
        for (i in map.indices) {
            val stack = Stack<Int>()
            stack.push(0)
            for (j in map[i].indices) {
                while (stack.size > 1 && map[i][stack.peek()] < map[i][j]) stack.pop()
                scoreMap[i][j] = scoreMap[i][j] * (j - stack.peek())
                stack.push(j)
            }
        }
    }

    private fun fromRightCheck(map: List<List<Int>>, scoreMap: List<MutableList<Long>>) {
        for (i in map.indices) {
            val stack = Stack<Int>()
            stack.push(map.lastIndex)
            for (j in map[i].lastIndex downTo 0) {
                while (stack.size > 1 && map[i][stack.peek()] < map[i][j]) stack.pop()
                scoreMap[i][j] = scoreMap[i][j] * (stack.peek() - j)
                stack.push(j)
            }
        }
    }

    private fun fromTopCheck(map: List<List<Int>>, scoreMap: List<MutableList<Long>>) {
        for (i in map[0].indices) {
            val stack = Stack<Int>()
            stack.push(0)
            for (j in map.indices) {
                while (stack.size > 1 && map[stack.peek()][i] < map[j][i]) stack.pop()
                scoreMap[j][i] = scoreMap[j][i] * (j - stack.peek())
                stack.push(j)
            }
        }
    }

    private fun fromBottomCheck(map: List<List<Int>>, scoreMap: List<MutableList<Long>>) {
        for (i in map[0].lastIndex downTo 0) {
            val stack = Stack<Int>()
            stack.push(map[0].lastIndex)
            for (j in map.lastIndex downTo 0) {
                while (stack.size > 1 && map[stack.peek()][i] < map[j][i]) stack.pop()
                scoreMap[j][i] = scoreMap[j][i] * (stack.peek() - j)
                stack.push(j)
            }
        }
    }
}