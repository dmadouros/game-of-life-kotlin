package me.dmadouros.gameoflife

data class Grid(val rows: List<List<Cell>>) {
    data class Offset(val x: Int, val y: Int)

    companion object {
        private val OFFSETS =
            listOf(
                Offset(-1, -1),
                Offset(-1, 0),
                Offset(-1, 1),
                Offset(0, -1),
                Offset(0, 1),
                Offset(1, -1),
                Offset(1, 0),
                Offset(1, 1)
            )
    }

    fun getCell(point: Point): Cell? =
        if (isInBounds(point))
            rows[point.y][point.x]
        else
            null

    fun getNeighbors(point: Point): Sequence<Cell> =
        OFFSETS.asSequence().map { offset ->
            val neighborX = point.x + offset.x
            val neighborY = point.y + offset.y
            val neighborPoint = Point(neighborX, neighborY)
            getCell(neighborPoint)
        }
            .filterNotNull()

    override fun toString(): String =
        rows.map { row ->
            row.map {
                if (it.isActive()) "x" else "_"
            }
        }.joinToString("\n") { row -> row.joinToString("") }

    private fun isInBounds(point: Point) =
        (point.y >= 0 && point.y < height()) && (point.x >= 0 && point.x < width())

    private fun height(): Int = rows.size

    private fun width(): Int = rows.first().size
}
