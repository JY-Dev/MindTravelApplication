package com.jydev.mindtravelapplication.domain.model

enum class Feeling {
    BAD, A_LITTLE_BAD, NORMAL, GOOD;
    companion object{
        fun getFeeling(index: Int): Feeling {
            return Feeling.values().find { it.ordinal == index } ?: throw IllegalArgumentException()
        }
    }
}