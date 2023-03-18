package com.jydev.mindtravelapplication.domain.model

import java.time.LocalDateTime

data class MoodRecord(val content : String ,val mood : Mood, val createdDate : LocalDateTime)