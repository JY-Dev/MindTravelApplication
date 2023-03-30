package com.jydev.mindtravelapplication.data.model

data class MindSharePostLikeResponse(
    val postId : Long,
    val member : MemberResponse,
    val createdDate : String
)