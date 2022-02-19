package me.dmadouros.gameoflife

data class Cell(val state: Boolean) {
    fun isActive(): Boolean = state
}
