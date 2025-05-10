package com.richbasoft.ollie.domain.calendar.enums

enum class Status {
    EMPTY,
    WORST,
    BAD,
    NORMAL,
    GOOD,
    BEST;

    companion object {
        fun fromSteps(steps: Int) = when {
            steps in 0 until 2500 -> WORST
            steps < 5000 -> BAD
            steps < 7500 -> NORMAL
            steps < 10000 -> GOOD
            steps >= 10000 -> BEST
            else -> EMPTY
        }

        fun stepsToPoint(steps: Int): Int {
            return if (steps % 100 >= 50) steps / 100 + 1
            else steps / 100
        }

    }

    fun statusToScore(): Int {
        return when (this) {
            WORST -> -2
            BAD -> -1
            GOOD -> 1
            BEST -> 2
            else -> 0 // NORMAL or EMPTY
        }
    }
}