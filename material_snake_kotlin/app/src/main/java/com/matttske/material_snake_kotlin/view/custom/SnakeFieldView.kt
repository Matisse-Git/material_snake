package com.matttske.material_snake_kotlin.view.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.matttske.material_snake_kotlin.game.Cell
import com.matttske.material_snake_kotlin.game.Snake
import kotlin.math.min

class SnakeFieldView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet){

    private var sqrtSize = 3
    private var size = 10

    //set in OnDraw
    private var cellSizePixels = 0f

    private var cells: List<Cell>? = null
    private var snake: Snake? = null

    private val thickLinePaint = Paint().apply{
        style = Paint.Style.STROKE
        color = Color.GREEN
        strokeWidth = 10f
    }

    private val thinLinePaint = Paint().apply{
        style = Paint.Style.STROKE
        color = Color.DKGRAY
        strokeWidth = 1f
    }

    private val snakePartPaint = Paint().apply{
        style = Paint.Style.FILL_AND_STROKE
        color = Color.rgb(89, 255, 51)
        isAntiAlias = true
    }

    private val snackPaint = Paint().apply{
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLUE
        isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val sizePixels = min(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(sizePixels, sizePixels + sizePixels / 2)
    }

    override fun onDraw(canvas: Canvas){
        updateMeasurements(width)
        drawLines(canvas)
        drawSnacks(canvas)
        drawSnake(canvas)
    }

    private fun updateMeasurements(width: Int){
        cellSizePixels = (width/size).toFloat()
    }

    private fun drawLines(canvas: Canvas){
        canvas.drawRect(0f,0f, width.toFloat(), height.toFloat(), thickLinePaint)

        for (i in 1 until size){
            val paintToUse = when (i % size){
                0 -> thickLinePaint
                else -> thinLinePaint
            }

            canvas.drawLine(
                    i * cellSizePixels,
                    0f,
                    i * cellSizePixels,
                    height.toFloat(),
                    paintToUse
            )
        }

        for (i in 1 until size + size / 2){
            val paintToUse = when (i % size + size / 2){
                0 -> thickLinePaint
                else -> thinLinePaint
            }

            canvas.drawLine(
                    0f,
                    i * cellSizePixels,
                    width.toFloat(),
                    i * cellSizePixels,
                    paintToUse
            )
        }


    }

    private fun drawSnake(canvas: Canvas){
        snake?.parts?.forEach {
            canvas.drawCircle(
                    it.posx * cellSizePixels + (cellSizePixels / 2),
                    it.posy * cellSizePixels + (cellSizePixels / 2),
                    (cellSizePixels / 2) * 0.8f,
                    snakePartPaint
            )
        }
    }

    private fun drawSnacks(canvas: Canvas){
        cells?.forEach {cell ->
            when (cell.value){
                "snack" -> canvas.drawCircle(
                        cell.posy * cellSizePixels + (cellSizePixels / 2),
                        cell.posx * cellSizePixels + (cellSizePixels / 2),
                        (cellSizePixels / 2) * 0.8f,
                        snackPaint
                )
            }
        }
    }

    fun updateSnake(snake: Snake){
        this.snake = snake
        invalidate()
    }

    fun updateCells(cells: List<Cell>){
        this.cells = cells
        invalidate()
    }
}