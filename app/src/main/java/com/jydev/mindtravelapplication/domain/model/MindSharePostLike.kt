package com.jydev.mindtravelapplication.domain.model

import java.time.LocalDateTime

data class MindSharePostLike(
    val postId : Long,
    val member : Member,
    val createdDate : LocalDateTime
)