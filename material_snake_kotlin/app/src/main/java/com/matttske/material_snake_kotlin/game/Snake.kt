package com.matttske.material_snake_kotlin.game

class Snake(var length: Int, var parts: MutableList<SnakePart>){
    fun getHeadPos() : Pair<Int, Int>{
        return parts[0].getPos()
    }

    fun updatePartsPos(newDirection : Pair<Int, Int>){
        var newHeadPos = Pair(parts[0].getPos().first + newDirection.first, parts[0].getPos().second + newDirection.second)

        parts.forEachIndexed { index, part ->
            when(index){
                0 -> {
                    part.updatePos(newHeadPos)
                }
                else -> {
                    part.updatePos(parts[index - 1].getLastPos())
                }
            }
        }
    }

    fun addSnakePart(){
        parts.add(SnakePart("body", parts.last().posx, parts.last().posy, parts.last().lastPosx, parts.last().lastPosy))
    }
}