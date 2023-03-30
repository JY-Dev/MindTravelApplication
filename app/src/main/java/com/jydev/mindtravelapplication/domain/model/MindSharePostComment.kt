package com.jydev.mindtravelapplication.domain.model

import java.time.LocalDateTime

data class MindSharePostComment(
    val commentId : Long,
    val content : String,
    val member : Member,
    val createdDate : LocalDateTime,
    val childComments : List<MindSharePostChildComment>
)
