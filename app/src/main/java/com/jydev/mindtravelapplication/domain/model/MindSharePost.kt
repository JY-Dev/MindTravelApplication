package com.jydev.mindtravelapplication.domain.model

import java.time.LocalDateTime

data class MindSharePost(
    val postId : Long,
    val member: Member,
    val title: String,
    val likeCount: Long,
    val viewCount: Long,
    val commentCount : Long,
    val createdDate: LocalDateTime
)
