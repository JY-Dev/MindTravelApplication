package com.jydev.mindtravelapplication.domain.model

enum class Mood {
    BAD, A_LITTLE_BAD, NORMAL, GOOD;
    companion object{
        fun getFeeling(index: Int): Mood {
            return Mood.values().find { it.ordinal == index } ?: throw IllegalArgumentException()
        }
    }
}