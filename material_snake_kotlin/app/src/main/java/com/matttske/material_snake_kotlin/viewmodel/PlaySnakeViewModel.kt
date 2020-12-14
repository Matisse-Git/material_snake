package com.matttske.material_snake_kotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.matttske.material_snake_kotlin.game.SnakeGame

class PlaySnakeViewModel : ViewModel(){
    val snakeGame = SnakeGame()
}