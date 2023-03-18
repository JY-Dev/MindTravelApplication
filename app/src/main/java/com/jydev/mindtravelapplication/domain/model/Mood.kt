package com.jydev.mindtravelapplication.domain.model

enum class Mood(val moodText : String) {
    BAD("나쁨"), A_LITTLE_BAD("살짝 나쁨"), NORMAL("보통"), GOOD("좋음");
    companion object{
        fun getFeeling(index: Int): Mood {
            return Mood.values().find { it.ordinal == index } ?: throw IllegalArgumentException()
        }
    }
}