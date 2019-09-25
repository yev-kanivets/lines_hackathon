package com.ykanivets.lines

import android.view.View

data class Cell(
    val y: Int = 0,
    val x: Int = 0,
    val view: View? = null,
    var type: Int = 0
)

const val DOTS_GENERATED_PER_TURN = 3
const val MAX_TYPE = 7
const val MIN_RESULT = 5
const val MULTIPLIER = 5

fun Array<Array<Cell>>.generateDots(numberOfDots: Int = DOTS_GENERATED_PER_TURN) {
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

fun Array<Array<Cell>>.isTurnPossible(): Boolean {
    val height = size - 1
    val width = this[0].size - 1

    var available = 0

    for (y in 0..height) {
        for (x in 0..width) {
            if (this[y][x].type == 0) available++
        }
    }

    return available >= DOTS_GENERATED_PER_TURN
}

fun Array<Array<Cell>>.checkResult(): Int {
    val height = size - 1
    val width = this[0].size - 1

    for (y in 0..height) {
        for (x in 0..width) {
            if (this[y][x].type != 0) {
                checkHorizontal(y, x)
                checkVertical(y, x)
            }
        }
    }

    return 0
}

private fun Array<Array<Cell>>.checkHorizontal(y: Int, x: Int): Int {
    val width = this[0].size - 1

    var left = 0
    var right = 0

    for (p in x downTo 0) {
        if (this[y][p].type == this[y][x].type) left++
        else break
    }

    for (p in x..width) {
        if (this[y][p].type == this[y][x].type) right++
        else break
    }

    val total = left + right - 1

    return if (total >= MIN_RESULT) {
        if (x - 1 >= 0) {
            for (p in (x - 1) downTo 0) {
                if (this[y][p].type == this[y][x].type) this[y][p].type = 0
                else break
            }
        }

        if (x + 1 <= width) {
            for (p in (x + 1)..width) {
                if (this[y][p].type == this[y][x].type) this[y][p].type = 0
                else break
            }
        }

        this[y][x].type = 0

        MIN_RESULT + (total - MIN_RESULT) * MULTIPLIER
    } else {
        0
    }
}

private fun Array<Array<Cell>>.checkVertical(y: Int, x: Int): Int {
    val height = size - 1

    var top = 0
    var down = 0

    for (p in y downTo 0) {
        if (this[p][x].type == this[y][x].type) top++
        else break
    }

    for (p in y..height) {
        if (this[p][x].type == this[y][x].type) down++
        else break
    }

    val total = top + down - 1

    return if (total >= MIN_RESULT) {
        if (y - 1 >= 0) {
            for (p in (y - 1) downTo 0) {
                if (this[p][x].type == this[y][x].type) this[p][x].type = 0
                else break
            }
        }

        if (y + 1 <= height) {
            for (p in (y + 1)..height) {
                if (this[p][x].type == this[y][x].type) this[p][x].type = 0
                else break
            }
        }

        this[y][x].type = 0

        MIN_RESULT + (total - MIN_RESULT) * MULTIPLIER
    } else {
        0
    }
}
