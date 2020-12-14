package com.matttske.material_snake_kotlin.game

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData


class SnakeGame {

    var snakeLiveData = MutableLiveData<Snake>()
    var cellsLiveData = MutableLiveData<List<Cell>>()
    var currentDirectionLiveData = MutableLiveData<Pair<Int, Int>>()

    private val field: Field
    private val snake: Snake

    private lateinit var currentDirection: Pair<Int, Int>

    init {
        val cells = List(10 * 15) {i -> Cell(i / 10, i % 10, when(i){
            13 -> "snack"
            else -> "empty"
        })}
        field = Field(Pair(10,15), cells)
        cellsLiveData.postValue(cells)

        val initPos = Pair(6, 7)
        val parts =  MutableList(4) {
            SnakePart("head", initPos.first, initPos.second, initPos.first - 1, initPos.second)
            SnakePart("body", initPos.first - 1, initPos.second, initPos.first - 2, initPos.second)
            SnakePart("body", initPos.first - 2, initPos.second, initPos.first - 3, initPos.second)
            SnakePart("body", initPos.first - 4, initPos.second, initPos.first - 5, initPos.second)
        }
        snake = Snake(parts.size, parts)
        snake.parts.add(2, SnakePart("body", initPos.first - 3, initPos.second, initPos.first - 4, initPos.second))
        currentDirection = Pair(0,0)
        updatePos()
    }

    fun updatePos(){
        snake.updatePartsPos(currentDirection)
        checkForSnack(snake.getHeadPos())
        snakeLiveData.postValue(snake)
        Log.d("Head Position", snake.getHeadPos().toString())
    }

    fun checkForSnack(snakeHeadPos: Pair<Int, Int>){
        if (field.getCell(snakeHeadPos.first, snakeHeadPos.second).value == "snack"){
            snake.addSnakePart()
        }
    }

    fun handleDirectionInput(directionIndex: Int){
        currentDirection = when (directionIndex) {
            0 -> Pair(-1, 0)
            1 -> Pair(0, -1)
            2 -> Pair(0, 1)
            3 -> Pair(1, 0)
            else -> Pair(0,0)
        }
        Log.d("Direction Changed", currentDirection.toString())

        currentDirectionLiveData.postValue(currentDirection)
    }
}