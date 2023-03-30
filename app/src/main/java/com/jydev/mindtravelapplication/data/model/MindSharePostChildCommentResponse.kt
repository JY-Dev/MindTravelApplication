package com.jydev.mindtravelapplication.data.model

data class MindSharePostChildCommentResponse(
    val commentId : Long,
    val content : String,
    val member : MemberResponse,
    val tagNickname : String,
    val createdDate : String
)
