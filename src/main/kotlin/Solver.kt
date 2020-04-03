import kotlin.math.cos
import kotlin.math.sin

class Solver {
    fun solve(input: Input): Result {
        val flightsForRes:ArrayList<Flight> = arrayListOf()

        input.flightEntries.map {
            val cosLat = cos(it.lat * Math.PI / 180.0)
            val sinLat = sin(it.lat * Math.PI / 180.0)
            val cosLon = cos(it.long * Math.PI / 180.0)
            val sinLon = sin(it.long * Math.PI / 180.0)
            val rad = 6371.0
            val x = rad * cosLat * cosLon
            val y = rad * cosLat * sinLon
            val z = rad * sinLat

            listOf(x,y,z)
        }


        flightsForRes.sort()
        return Result(flightsForRes)
    }
}

data class Flight(
    val from:String,
    val to:String,
    var flights:Int
):Comparable<Flight> {

    override fun toString(): String {
        return "$from $to $flights"
    }

    override fun compareTo(other: Flight): Int {
        if (this.from > other.from) return 1
        if (this.from < other.from) return -1
        if (this.to > other.to) return 1
        if (this.to < other.to) return -1
        return 0
    }
}
data class Result(
    val flights:List<Flight>
) {
    override fun toString(): String {
        return flights.joinToString("\n")
    }
}