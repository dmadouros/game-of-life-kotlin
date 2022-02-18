package me.dmadouros.gameoflife

interface Rule {
    fun isApplicable(isActive: Boolean, activeNeighborCount: Int): Boolean

    fun apply(): Boolean
}
