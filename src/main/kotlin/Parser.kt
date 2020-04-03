import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import kotlin.collections.ArrayList

class Parser {
    fun parse(file: File): Input {
        var line: String
        var data: Array<String>

        BufferedReader(FileReader(file)).use { reader ->
            line = reader.readLine()
            data = line.split(" ".toRegex()).toTypedArray()
            val nFlights = data[0].toInt()
            val flightEntries: MutableList<TimeRequests> = ArrayList()
            for (i in 0 until nFlights) {
                val flightEntryString = reader.readLine()
                val flightEntryData = flightEntryString.split(" ".toRegex()).toTypedArray()
                flightEntries.add(
                    TimeRequests(
                        flightId = flightEntryData[0].toInt(),
                        timestamp = flightEntryData[1].toInt()
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

fun parseFlight(flightId: Int): FlightData {
    BufferedReader(FileReader(File("input/usedFlights/$flightId.csv"))).use { reader ->
        val startAirport = reader.readLine()
        val endAirport = reader.readLine()
        val takeOffTimestamp = reader.readLine().toInt()
        val nData = reader.readLine().toInt()
        val data = mutableListOf<FlightSnapshot>()

        for (i in 0 until nData) {
            val snapshot = reader.readLine().split(",")
            data.add(
                FlightSnapshot(
                    snapshot[0].toInt(),
                    snapshot[1].toDouble(),
                    snapshot[2].toDouble(),
                    snapshot[3].toDouble()
                )
            )
        }


        return FlightData(startAirport, endAirport, takeOffTimestamp, nData, data)

        //reader.readLine().split(" ".toRegex()).toTypedArray()
    }
}

data class Input(
    val nFlights: Int,
    val timeRequests: List<TimeRequests>
)

data class TimeRequests(
    val flightId: Int,
    val timestamp: Int
)

data class FlightData(
    val startAirport: String,
    val endAirport: String,
    val takeOffTimestamp: Int,
    val nData: Int,
    val data: List<FlightSnapshot>
)

data class FlightSnapshot(
    val timestampOffset: Int,
    val lat: Double,
    val long: Double,
    val altitude: Double
)
