import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import kotlin.collections.ArrayList

class Parser {
    fun parse(file: File): Input {
        BufferedReader(FileReader(file)).use { reader ->
            val transferRange = reader.readLine().toDouble()
            val nFlights = reader.readLine().toInt()
            val flightIds: MutableList<Int> = ArrayList()
            for (i in 0 until nFlights) {
                flightIds.add(reader.readLine().toInt())
            }
            return Input(
                transferRange,
                nFlights,
                flightIds
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
        return FlightData(flightId, startAirport, endAirport, takeOffTimestamp, nData, data)
    }
}

data class Input(
    val transferRange: Double,
    val nFlights: Int,
    val flightIds: List<Int>
)

data class FlightData(
    val flightId: Int,
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
