import java.math.RoundingMode

class Solver {
    fun solve(input: Input): Result {
        return Result(input.timeRequests.map { request ->
            val flight = parseFlight(request.flightId)
            val lowerTs = flight.data.findLast { request.timestamp > it.timestampOffset + flight.takeOffTimestamp }!!
            val upperTs = flight.data.find { request.timestamp <= it.timestampOffset + flight.takeOffTimestamp }!!

            val to = upperTs.timestampOffset + flight.takeOffTimestamp
            val from = lowerTs.timestampOffset + flight.takeOffTimestamp

            val max = to - from
            val act = to - request.timestamp


            val pos = act.toDouble() / max.toDouble()


            Coordinate(
                linearInterpolate(upperTs.lat, lowerTs.lat, pos),
                linearInterpolate(upperTs.long, lowerTs.long, pos),
                linearInterpolate(upperTs.altitude, lowerTs.altitude, pos)
            )
        })
    }
}

data class Coordinate(
    val lat: Double,
    val long: Double,
    val alt: Double
) {
    override fun toString(): String {
        return "$lat $long $alt"
    }
}

data class Result(
    val coords: List<Coordinate>
) {
    override fun toString(): String {
        return coords.joinToString("\n")
    }
}

fun Double.round(decimals: Int): Double {
    return this.toBigDecimal().setScale(decimals, RoundingMode.FLOOR).toDouble()
}

fun linearInterpolate(a: Double, b: Double, f: Double): Double {
    return a + f * (b - a)
}