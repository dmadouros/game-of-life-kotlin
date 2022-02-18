package me.dmadouros.gameoflife

data class Game(private val grid: Grid) {
    companion object {
        private val RULE_1 = object : Rule {
            override fun isApplicable(isActive: Boolean, activeNeighborCount: Int): Boolean =
                isActive && activeNeighborCount < 2

            override fun apply() = false
        }
        private val RULE_2 = object : Rule {
            override fun isApplicable(isActive: Boolean, activeNeighborCount: Int): Boolean =
                isActive && listOf(2, 3).contains(activeNeighborCount)

            override fun apply() = true
        }
        private val RULE_3 = object : Rule {
            override fun isApplicable(isActive: Boolean, activeNeighborCount: Int): Boolean =
                isActive && activeNeighborCount > 3

            override fun apply() = false
        }
        private val RULE_4 = object : Rule {
            override fun isApplicable(isActive: Boolean, activeNeighborCount: Int): Boolean =
                !isActive && activeNeighborCount == 3

            override fun apply() = true
        }
        private val RULES = listOf(
            RULE_1,
            RULE_2,
            RULE_3,
            RULE_4
        )
    }

    fun tick(): Game =
        Game(
            Grid(
                grid.rows.mapIndexed { y, row ->
                    row.mapIndexed { x, _ ->
                        val currentPoint = Point(x, y)
                        val currentCell = grid.getCell(currentPoint)!!
                        val activeNeighborCount = countActiveNeighbors(currentPoint)
                        val nextState =
                            RULES.find { rule -> rule.isApplicable(currentCell.isActive(), activeNeighborCount) }
                                ?.apply()
                                ?: currentCell.state
                        Cell(nextState)
                    }
                }
            )
        )

    override fun toString() = grid.toString()

    private fun countActiveNeighbors(point: Point): Int =
        grid.getNeighbors(point)
            .filter(Cell::isActive)
            .toList()
            .size
}
