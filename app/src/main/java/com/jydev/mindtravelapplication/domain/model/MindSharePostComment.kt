package com.jydev.mindtravelapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class MindSharePostComment(
    override val commentId : Long,
    override val content : String,
    override val member : Member,
    override val createdDate : LocalDateTime,
    val childComments : List<MindSharePostChildComment>
) : PostComment(), Parcelable
