package com.richbasoft.ollie.domain.ollie.enums

enum class Status(
    val status: String
) {
    WORST("아파"),
    BAD("안좋아"),
    NORMAL("보통"),
    GOOD("좋아"),
    BEST("최고");

    fun scoreToStatus(score: Int): Status {
        return when {
            score <= -6 -> WORST
            score <= -2 -> BAD
            score <= 2 -> NORMAL
            score <= 6 -> GOOD
            else -> BEST
        }
    }
}