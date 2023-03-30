package com.jydev.mindtravelapplication.data.model

data class MindSharePostResponse(
    val postId : Long,
    val member: MemberResponse,
    val title: String,
    val likeCount: Long,
    val viewCount: Long,
    val commentCount : Long,
    val createdDate: String
)