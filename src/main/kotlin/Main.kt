import java.io.File

var FILE_PATH_KEY = "-f"
var FILE_PATH_KEY_LONG = "--file"

var WORD_KEY = "-w"
var WORD_KEY_LONG = "--word"

fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")

    var filePath: String? = null;
    var word: String? = null;

    for ((index, value) in args.withIndex()) {
        if (value == FILE_PATH_KEY || value == FILE_PATH_KEY_LONG) {
            filePath = args[index + 1];

            if (filePath.isNullOrEmpty())
                throw Exception("argument file is incorrect")
        }

        if (value == WORD_KEY || value == WORD_KEY_LONG) {
            word = args[index + 1];

            if (word.isNullOrEmpty())
                throw Exception("argument word is incorrect")
        }
    }

    if (filePath.isNullOrEmpty())
        throw Exception("file path is required");

    if (word.isNullOrEmpty())
        throw Exception("word is required")

    val lines = parseFile(filePath, word);

    println(lines);
}

fun parseFile(fileName: String, findWord: String): ArrayList<Int> {
    val lines = File(fileName).useLines { it.toList() }

    val foundLines: ArrayList<Int> = ArrayList();

    for ((index, line) in lines.withIndex()) {
        for (value in line.split(" ")) {
            if (value == findWord) foundLines.add(index + 1)
        }
    }

    return foundLines
}