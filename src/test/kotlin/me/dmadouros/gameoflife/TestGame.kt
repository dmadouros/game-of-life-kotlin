package me.dmadouros.gameoflife

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class TestGame {

    private fun toGrid(text: String): Grid {
        val lines = text.split("\n")
        return Grid(
            lines.map { line ->
                line.map { char ->
                    if (char == 'x') Cell(true) else Cell(false)
                }
            }
        )
    }

    @Test
    fun testExample1() {
        val grid = toGrid(
            """
            ___
            _x_
            ___
            """.trimIndent()
        )
        val game = Game(grid)

        val actual = game.tick()

        assertThat(actual).isEqualTo(
            Game(
                toGrid(
                    """
            ___
            ___
            ___
                    """.trimIndent()
                )
            )
        )
    }

    @Test
    fun testExample2() {
        val grid = toGrid(
            """
            xx_
            _x_
            ___
            """.trimIndent()
        )
        val game = Game(grid)

        val actual = game.tick()

        assertThat(actual).isEqualTo(
            Game(
                toGrid(
                    """
            xx_
            xx_
            ___
                    """.trimIndent()
                )
            )
        )
    }

    @Test
    fun testExample3() {
        val grid = toGrid(
            """
            xxx
            _x_
            ___
            """.trimIndent()
        )
        println(grid)
        val game = Game(grid)

        val actual = game.tick()

        assertThat(actual).isEqualTo(
            Game(
                toGrid(
                    """
            xxx
            xxx
            ___
                    """.trimIndent()
                )
            )
        )
    }

    @Test
    fun testExample4() {
        val grid = toGrid(
            """
            xxx
            _x_
            _x_
            """.trimIndent()
        )
        val game = Game(grid)

        val actual = game.tick()

        assertThat(actual).isEqualTo(
            Game(
                toGrid(
                    """
            xxx
            ___
            ___
                    """.trimIndent()
                )
            )
        )
    }

    @Test
    fun testBlock() {
        val grid = toGrid(
            """
            ____
            _xx_
            _xx_
            ____
            """.trimIndent()
        )
        val game = Game(grid)

        val actual = game.tick()

        assertThat(actual).isEqualTo(Game(grid))
    }

    @Test
    fun testBeehive() {
        val grid = toGrid(
            """
            ______
            __xx__
            _x__x_
            __xx__
            ______
            """.trimIndent()
        )
        val game = Game(grid)

        val actual = game.tick()

        assertThat(actual).isEqualTo(Game(grid))
    }

    @Test
    fun testLoaf() {
        val grid = toGrid(
            """
            ______
            __xx__
            _x__x_
            __x_x_
            ___x__
            ______
            """.trimIndent()
        )
        val game = Game(grid)

        val actual = game.tick()

        assertThat(actual).isEqualTo(Game(grid))
    }

    @Test
    fun testBoat() {
        val grid = toGrid(
            """
            _____
            _xx__
            _x_x_
            __x__
            _____
            """.trimIndent()
        )
        val game = Game(grid)

        val actual = game.tick()

        assertThat(actual).isEqualTo(Game(grid))
    }

    @Test
    fun testTub() {
        val grid = toGrid(
            """
            _____
            __x__
            _x_x_
            __x__
            _____
            """.trimIndent()
        )
        val game = Game(grid)

        val actual = game.tick()

        assertThat(actual).isEqualTo(Game(grid))
    }

    @Test
    fun testBlinker() {
        val vertical = toGrid(
            """
            _____
            __x__
            __x__
            __x__
            _____
            """.trimIndent()
        )
        val horizontal = toGrid(
            """
            _____
            _____
            _xxx_
            _____
            _____
            """.trimIndent()
        )
        val game = Game(vertical)

        val period1 = game.tick()
        assertThat(period1).isEqualTo(Game(horizontal))

        val period2 = period1.tick()
        assertThat(period2).isEqualTo(Game(vertical))
    }

    @Test
    fun testToad() {
        val closed = toGrid(
            """
            ______
            ______
            __xxx_
            _xxx__
            ______
            ______
            """.trimIndent()
        )
        val open = toGrid(
            """
            ______
            ___x__
            _x__x_
            _x__x_
            __x___
            ______
            """.trimIndent()
        )
        val game = Game(closed)

        val period1 = game.tick()
        assertThat(period1).isEqualTo(Game(open))

        val period2 = period1.tick()
        assertThat(period2).isEqualTo(Game(closed))
    }

    @Test
    fun testToString() {
        val grid = Grid(
            listOf(
                listOf(Cell(true), Cell(true), Cell(true)),
                listOf(Cell(false), Cell(true), Cell(false)),
                listOf(Cell(false), Cell(false), Cell(false)),
            )
        )
        val game = Game(grid)

        val actual = game.toString()

        assertThat(actual).isEqualTo("xxx\n_x_\n___")
    }
}
