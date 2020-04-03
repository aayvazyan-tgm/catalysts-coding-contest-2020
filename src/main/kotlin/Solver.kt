import java.math.RoundingMode
import kotlin.math.cos
import kotlin.math.sin

class Solver {
    fun solve(input: Input): Result {
        val flightsForRes = input.flightEntries.map {
            val cosLat = cos(it.lat * Math.PI / 180.0)
            val sinLat = sin(it.lat * Math.PI / 180.0)
            val cosLon = cos(it.long * Math.PI / 180.0)
            val sinLon = sin(it.long * Math.PI / 180.0)
            val rad = 6371.0 * 1000 + it.altitude
            val x = rad * cosLat * cosLon
            val y = rad * cosLat * sinLon
            val z = rad * sinLat

            Coordinate(x.round(9),y.round(9),z.round(9))
        }
        return Result(flightsForRes)
    }
}
data class Coordinate(
    val x:Double,
    val y:Double,
    val z:Double
){
    override fun toString(): String {
        return "$x $y $z"
    }
}

data class Result(
    val coords:List<Coordinate>
) {
    override fun toString(): String {
        return coords.joinToString("\n")
    }
}

fun Double.round(decimals: Int): Double {
    return  this.toBigDecimal().setScale(decimals, RoundingMode.FLOOR).toDouble()
}