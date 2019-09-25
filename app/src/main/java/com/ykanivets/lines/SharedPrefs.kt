package com.ykanivets.lines

import android.content.Context

const val APPLICATION = "application"
const val KEY_MATRIX = "key_matrix"
const val KEY_TURNS = "key_turns"
const val KEY_SCORE = "key_score"

fun Context.writeTurns(turns: Int) {
    val sharedPreferences = getSharedPreferences(APPLICATION, Context.MODE_PRIVATE)
    sharedPreferences.edit().putInt(KEY_TURNS, turns).apply()
}

fun Context.readTurns(): Int {
    val sharedPreferences = getSharedPreferences(APPLICATION, Context.MODE_PRIVATE)
    return sharedPreferences.getInt(KEY_TURNS, 0)
}

fun Context.writeScore(score: Int) {
    val sharedPreferences = getSharedPreferences(APPLICATION, Context.MODE_PRIVATE)
    sharedPreferences.edit().putInt(KEY_SCORE, score).apply()
}

fun Context.readScore(): Int {
    val sharedPreferences = getSharedPreferences(APPLICATION, Context.MODE_PRIVATE)
    return sharedPreferences.getInt(KEY_SCORE, 0)
}

fun Context.writeMatrix(matrix: Array<Array<Cell>>) {
    var string = ""

    matrix.forEach { row ->
        row.forEach { cell ->
            string += " $cell"
        }
    }

    val sharedPreferences = getSharedPreferences(APPLICATION, Context.MODE_PRIVATE)
    sharedPreferences.edit().putString(KEY_MATRIX, string).apply()
}

fun Context.readMatrix(): Array<Array<Cell>> {
    val string = getSharedPreferences(APPLICATION, Context.MODE_PRIVATE).getString(KEY_MATRIX, null)
    val result = Array(9) { Array(9) { Cell() } }

    return result
}
