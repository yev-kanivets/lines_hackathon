package com.ykanivets.lines

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.btnDraw
import kotlinx.android.synthetic.main.activity_main.canvas

class MainActivity : AppCompatActivity() {

    data class Cell(
        val y: Int = 0,
        val x: Int = 0,
        val view: View? = null,
        var type: Int = 0
    )

    private val matrix = Array(9) { Array(9) { Cell() } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCanvas()

        matrix[1][1].type = 2

        btnDraw.setOnClickListener { draw() }
    }

    private fun initCanvas() {
        for (y in 0..8) {
            val layout = LinearLayout(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    1f
                )
                orientation = LinearLayout.HORIZONTAL
            }
            for (x in 0..8) {
                val view = View(this).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        1f
                    )
                    background = ColorDrawable(Color.GRAY)
                }
                matrix[y][x] = Cell(y, x, view)
                layout.addView(view)
            }
            canvas.addView(layout)
        }
    }

    private fun draw() {
        matrix.forEach { row ->
            row.forEach { cell ->
                cell.view?.apply {
                    val color = when (cell.type) {
                        0 -> Color.GRAY
                        1 -> Color.GREEN
                        2 -> Color.BLUE
                        3 -> Color.RED
                        4 -> Color.YELLOW
                        5 -> Color.MAGENTA
                        6 -> Color.CYAN
                        else -> Color.GRAY
                    }
                    background = ColorDrawable(color)
                }
            }
        }
    }
}
