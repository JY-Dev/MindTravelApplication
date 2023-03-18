package com.jydev.mindtravelapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class MoodRecord(
    val id: Long,
    val content: String,
    val mood: Mood,
    val createdDate: LocalDateTime
) : Parcelable