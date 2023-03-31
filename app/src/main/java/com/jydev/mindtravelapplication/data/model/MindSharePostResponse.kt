package com.jydev.mindtravelapplication.data.model

import com.jydev.mindtravelapplication.domain.model.MindSharePostCategory

data class MindSharePostResponse(
    val postId : Long,
    val member: MemberResponse,
    val title: String,
    val category : MindSharePostCategory,
    val likeCount: Long,
    val viewCount: Long,
    val commentCount : Long,
    val createdDate: String
)