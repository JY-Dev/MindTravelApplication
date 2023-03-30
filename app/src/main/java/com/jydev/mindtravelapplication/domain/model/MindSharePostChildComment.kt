package com.jydev.mindtravelapplication.domain.model

import java.time.LocalDateTime

data class MindSharePostChildComment(
    val commentId : Long,
    val content : String,
    val member : Member,
    val tagNickname : String,
    val createdDate : LocalDateTime
)
