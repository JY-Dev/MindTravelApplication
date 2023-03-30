package com.jydev.mindtravelapplication.data.model

data class MindSharePostCommentResponse(
    val commentId : Long,
    val content : String,
    val member : MemberResponse,
    val createdDate : String,
    val childComments : List<MindSharePostChildCommentResponse>
)
