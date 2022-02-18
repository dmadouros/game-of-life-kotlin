package me.dmadouros.gameoflife

data class Cell(val state: Boolean) {
    fun isActive() = state
}
