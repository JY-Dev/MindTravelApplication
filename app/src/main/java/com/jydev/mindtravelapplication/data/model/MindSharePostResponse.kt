package com.jydev.mindtravelapplication.data.model

data class MindSharePostResponse(
    val postId : Long,
    val nickname: String,
    val title: String,
    val likeCount: Long,
    val viewCount: Long,
    val createdDate: String
)