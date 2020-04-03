import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*
import kotlin.collections.ArrayList

class Parser {
    fun parse(file: File): Input {
        var line: String
        var data: Array<String>

        BufferedReader(FileReader(file)).use { reader ->
            line = reader.readLine()
            data = line.split(" ".toRegex()).toTypedArray()
            val nFlights = data[0].toInt()
            val flightEntries: MutableList<FlightEntry> = ArrayList()
            for (i in 0 until nFlights) {
                val flightEntryString = reader.readLine()
                val flightEntryData = flightEntryString.split(",".toRegex()).toTypedArray()
                flightEntries.add(
                    FlightEntry(
                        lat = flightEntryData[0].toDouble(),
                        long = flightEntryData[1].toDouble(),
                        altitude = flightEntryData[2].toDouble()
                    )
                )
            }
            return Input(
                nFlights,
                flightEntries
            )
        }
    }
}

data class Input(
    val nFlights: Int,
    val flightEntries: List<FlightEntry>
)

data class FlightEntry(
    val lat: Double,
    val long: Double,
    val altitude: Double
)
