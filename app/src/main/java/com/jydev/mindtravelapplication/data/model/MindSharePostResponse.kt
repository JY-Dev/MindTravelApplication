package com.jydev.mindtravelapplication.data.model

data class MindSharePostResponse(
    val nickname: String,
    val title: String,
    val content: String,
    val likeCount: Long,
    val viewCount: Long,
    val createdDate: String
)