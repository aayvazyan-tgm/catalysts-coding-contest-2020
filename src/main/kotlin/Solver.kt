class Solver {
    fun solve(input: Input): Result {
        val result = Result()

        result.minTimestamp = input.flightEntries.minBy { it.timestamp }?.timestamp
        result.maxTimestamp = input.flightEntries.maxBy { it.timestamp }?.timestamp

        result.minLat = input.flightEntries.minBy { it.lat }?.lat
        result.maxLat = input.flightEntries.maxBy { it.lat }?.lat

        result.minLong = input.flightEntries.minBy { it.long }?.long
        result.maxLong = input.flightEntries.maxBy { it.long }?.long

        result.maxAltitude = input.flightEntries.maxBy { it.altitude }?.altitude

        return result
    }
}

data class Result(
    var minTimestamp: Int? = null,
    var maxTimestamp: Int? = null,

    var minLat: Float? = null,
    var maxLat: Float? = null,

    var minLong: Float? = null,
    var maxLong: Float? = null,

    var maxAltitude: Float? = null

) {
    override fun toString(): String {
        return """
            $minTimestamp $maxTimestamp
            $minLat $maxLat
            $minLong $maxLong
            $maxAltitude
        """.trimIndent()
    }
}