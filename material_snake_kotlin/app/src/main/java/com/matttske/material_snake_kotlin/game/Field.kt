package com.matttske.material_snake_kotlin.game

class Field(val size: Pair<Int, Int>, val cells: List<Cell>) {
    fun getCell(posx: Int, posy: Int) = cells[posx + posy * size.first]
}