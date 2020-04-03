import java.math.RoundingMode

class Solver {
    fun solve(input: Input): Result {
        input.transferRange
        val flightData = input.flightIds.map { parseFlight(it) }

        input.flightIds.map { flugId ->
            val flightData = flightData.filter { it.flightId != flugId }
            flightData.forEach {
                println(it)
                //TODO compare all timestamps with each other and intepolate
            }
        }

    return Result(emptyList())
    }
}

data class FlightCollision(
    val flugA: String,
    val flugB: String,
    val delayOfB: Int,
    val timePeriod: String
) {
    override fun toString(): String {
        return "$flugA $flugB $delayOfB $timePeriod"
    }
}

data class Result(
    val flightCollisions: List<FlightCollision>
) {
    override fun toString(): String {
        return flightCollisions.joinToString("\n")
    }
}

fun linearInterpolate(a: Double, b: Double, f: Double): Double {
    return a + f * (b - a)
}