package com.jydev.mindtravelapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class MindSharePostDetail(
    val postId : Long,
    val title : String,
    val content : String,
    val category : MindSharePostCategory,
    val viewCount : Long,
    val commentCount : Long,
    val createdDate : LocalDateTime,
    val member : Member,
    val comments : List<MindSharePostComment>,
    val likes : List<MindSharePostLike>
) : Parcelable