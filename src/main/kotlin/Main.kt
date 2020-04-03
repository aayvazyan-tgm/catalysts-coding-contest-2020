import java.io.File

class Main {
    val files = listOf(
        "level1_example.in"
        , "level1_1.in"
        , "level1_2.in"
        , "level1_3.in"
        , "level1_4.in"
        , "level1_5.in"
    )

    constructor() {
        files
            //.parallelStream()
            .forEach {
                println("Starting $it")
                val input = Parser().parse(File("input/$it"))
                val result = Solver().solve(input)
                printresult(result, File("output/sol_$it"))
                println("Done with $it")
            }
    }

    private fun printresult(result: Result, file: File) {
        file.delete()
        file.createNewFile()

        file.writeText(result.toString())
    }
}

fun main() {
    Main()
    Runtime.getRuntime().exec("zipper.bat")
}