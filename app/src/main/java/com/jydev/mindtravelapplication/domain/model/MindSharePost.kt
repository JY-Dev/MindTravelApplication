package com.jydev.mindtravelapplication.domain.model

import java.time.LocalDateTime

data class MindSharePost(
    val postId : Long,
    val nickname: String,
    val title: String,
    val likeCount: Long,
    val viewCount: Long,
    val createdDate: LocalDateTime
)
