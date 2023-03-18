package com.jydev.mindtravelapplication.domain.model

enum class Mood(val moodText: String, val detailText: String) {
    BAD("나쁨", "좋지 않은 시간이었어요"),
    A_LITTLE_BAD("살짝 나쁨", "안 좋은 일이 있으신가요?"),
    NORMAL(
        "보통",
        "평범한 시간이었어요"
    ),
    GOOD("좋음","좋은 시간이었어요");

    companion object {
        fun getFeeling(index: Int): Mood {
            return Mood.values().find { it.ordinal == index } ?: throw IllegalArgumentException()
        }
    }
}