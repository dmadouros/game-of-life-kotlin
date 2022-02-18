import me.dmadouros.gameoflife.Cell
import me.dmadouros.gameoflife.Game
import me.dmadouros.gameoflife.Grid

fun main() {
    println("==========")
    listOf(
        Grid(
            listOf(
                listOf(Cell(false), Cell(false), Cell(false)),
                listOf(Cell(false), Cell(true), Cell(false)),
                listOf(Cell(false), Cell(false), Cell(false)),
            )
        ),
        Grid(
            listOf(
                listOf(Cell(true), Cell(true), Cell(false)),
                listOf(Cell(false), Cell(true), Cell(false)),
                listOf(Cell(false), Cell(false), Cell(false)),
            )
        ),
        Grid(
            listOf(
                listOf(Cell(true), Cell(true), Cell(true)),
                listOf(Cell(false), Cell(true), Cell(false)),
                listOf(Cell(false), Cell(false), Cell(false)),
            )
        ),
        Grid(
            listOf(
                listOf(Cell(true), Cell(true), Cell(true)),
                listOf(Cell(false), Cell(true), Cell(false)),
                listOf(Cell(false), Cell(true), Cell(false)),
            )
        ),
    )
        .map { grid -> Game(grid) }
        .forEach { game ->
            game
                .also { println("Before:") }
                .also { println(it) }
                .let { it.tick() }
                .also { println() }
                .also { println("After:") }
                .also { println(it) }
                .also { println("==========") }
        }
}
