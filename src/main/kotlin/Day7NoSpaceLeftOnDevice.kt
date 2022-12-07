import kotlin.properties.Delegates

class Day7NoSpaceLeftOnDevice {
    interface Component {
        var name: String
        var size: Long
        var parent: Component?
    }

    data class File(
        override var name: String,
        override var size: Long,
        override var parent: Component? = null,
    ) : Component

    data class Folder(
        override var name: String,
        override var size: Long,
        override var parent: Component? = null,
    ) : Component {
        val children: MutableList<Component> = mutableListOf()
    }

    private lateinit var root: Component
    private var part1Sum = 0L

    fun part1(commands: List<String>): Long {
        makeFileSystem(commands)
        recursiveCounting(root as Folder)
        return part1Sum
    }

    private fun makeFileSystem(commands: List<String>) {
        lateinit var current: Component
        var willDisplay = false

        commands.forEach {
            val str = it.split(" ")
            if (it == "$ cd /") {
                if (!::root.isInitialized) {
                    root = Folder("root", 0)
                }
                current = root
            } else if (it == "$ ls") {
                willDisplay = true
            } else if (str[0] == "$" && str[1] == "cd") {
                willDisplay = false
                if (str[2] == "..") {
                    current = requireNotNull(current.parent)
                } else {
                    val nextDir = requireNotNull((current as Folder).children.find { it.name == str[2] })
                    nextDir.parent = current
                    current = nextDir
                }
            } else if (willDisplay) {
                val folder = current as Folder
                folder.children.add(if (str[0] == "dir") Folder(str[1], 0) else File(str[1], str[0].toLong()))
            }
        }
    }

    private fun recursiveCounting(root: Folder) {
        if (root.children.any { it is Folder }) {
            root.children.filterIsInstance<Folder>().forEach(::recursiveCounting)
        }
        root.size = root.children.sumOf { it.size }
        if (root.size <= 100000) {
            part1Sum += root.size
        }
    }

    private var remained by Delegates.notNull<Long>()
    private var part2Size = Long.MAX_VALUE
    private val AT_LEAST_SIZE = 30000000

    fun part2(commands: List<String>): Long {
        val TOTAL_SIZE = 70000000
        part1(commands)
        remained = TOTAL_SIZE - root.size
        foundTheClosestFolder(root as Folder)
        return part2Size
    }

    private fun foundTheClosestFolder(root: Folder) {
        if (remained + root.size >= AT_LEAST_SIZE && part2Size > root.size) {
            part2Size = root.size
        }
        if (root.children.any { it is Folder }) {
            root.children.filterIsInstance<Folder>().forEach(::foundTheClosestFolder)
        }
    }
}