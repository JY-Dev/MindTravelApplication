package com.jydev.mindtravelapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class MindSharePostLike(
    val postId : Long,
    val member : Member,
    val createdDate : LocalDateTime
) : Parcelable