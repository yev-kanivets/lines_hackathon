package com.ykanivets.lines

import android.view.View

data class Cell(
    val y: Int = 0,
    val x: Int = 0,
    val view: View? = null,
    var type: Int = 0
)

const val MAX_TYPE = 7

fun Array<Array<Cell>>.generateDots(numberOfDots: Int = 3) {
    val height = size - 1
    val width = this[0].size - 1

    var counter = numberOfDots

    while (counter != 0) {
        val y = (0..height).shuffled().first()
        val x = (0..width).shuffled().first()

        if (this[y][x].type == 0) {
            counter--
            this[y][x].type = (1..MAX_TYPE).shuffled().first()
        }
    }
}
