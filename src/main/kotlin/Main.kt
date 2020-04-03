import java.io.File

class Main {
    val files = listOf(
        "level5_example.in"
        , "level5_1.in"
        , "level5_2.in"
        , "level5_3.in"
        , "level5_4.in"
        , "level5_5.in"
    )

    constructor() {
        files
            //.parallelStream()
            .forEach {
                println("Starting $it")
                val input = Parser().parse(File("input/$it"))
                val result = Solver().solve(input)
                printresult(result, File("output/sol_$it".dropLast(2) + "out"))
                println("Done with $it")
            }
    }

    private fun printresult(result: Result, file: File) {
        file.mkdirs()
        file.delete()
        file.createNewFile()

        file.writeText(result.toString())
    }
}

fun main() {
    Main()
    Runtime.getRuntime().exec("zipper.bat")
}