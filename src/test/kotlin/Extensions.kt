import java.io.IOException

@Throws(IOException::class)
inline fun <T : Any> T.readFileWithNewLineFromResources(fileName: String, eachLine: (String) -> Unit) {
    javaClass.classLoader
        ?.getResourceAsStream(fileName)
        ?.bufferedReader()
        ?.readLines()
        ?.forEach(eachLine)
}
