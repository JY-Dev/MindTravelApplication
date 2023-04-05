package com.jydev.mindtravelapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class MindSharePostChildComment(
    override val commentId : Long,
    override val content : String,
    override val member : Member,
    val parentCommentId : Long,
    val tagNickname : String,
    override val createdDate : LocalDateTime
) : PostComment(), Parcelable
