package com.matttske.material_snake_kotlin.game

class SnakePart(var type: String,var posx: Int, var posy: Int, var lastPosx: Int, var lastPosy: Int) {
    fun getPos() : Pair<Int, Int>{
        return Pair(posx, posy)
    }

    fun getLastPos() : Pair<Int, Int>{
        return Pair(lastPosx, lastPosy)
    }

    fun updatePos(newPos: Pair<Int, Int>){
        lastPosx = posx
        lastPosy = posy

        posx = newPos.first
        posy = newPos.second
    }
}