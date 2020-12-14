package com.matttske.material_snake_kotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.matttske.material_snake_kotlin.R
import com.matttske.material_snake_kotlin.viewmodel.PlaySnakeViewModel
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.matttske.material_snake_kotlin.game.Cell
import com.matttske.material_snake_kotlin.game.Snake
import kotlinx.android.synthetic.main.activity_main.*

class PlaySnakeActivity : AppCompatActivity() {

    private lateinit var viewModel: PlaySnakeViewModel
    private lateinit var directionButtons: List<ImageButton>

    lateinit var mainHandler: Handler

    private val updateSnakePos = object : Runnable {
        override fun run() {
            viewModel.snakeGame.updatePos()
            mainHandler.postDelayed(this, 500)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        setupActionBar()

        viewModel =  ViewModelProviders.of(this).get(PlaySnakeViewModel::class.java)
        viewModel.snakeGame.snakeLiveData.observe(this, Observer { updateSnakePos(it) })
        viewModel.snakeGame.cellsLiveData.observe(this, Observer { updateCells(it) })

        directionButtons = listOf(leftButton, upButton, downButton, rightButton)
        directionButtons.forEachIndexed { index, button ->
            button.setOnClickListener{
                viewModel.snakeGame.handleDirectionInput(index)
            }
        }

        mainHandler = Handler(Looper.getMainLooper())
    }

    private fun setupActionBar() {
        val actionbar = supportActionBar
        actionbar!!.title = "Play Snake"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        return true
    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(updateSnakePos)
    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(updateSnakePos)
    }

    private fun updateSnakePos(snake: Snake?) = snake?.let{
        snakeFieldView.updateSnake(snake)
    }

    private fun updateCells(cells: List<Cell>?) = cells?.let{
        snakeFieldView.updateCells(cells)
    }
}