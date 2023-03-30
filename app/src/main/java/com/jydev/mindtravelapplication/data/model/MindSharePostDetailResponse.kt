package com.jydev.mindtravelapplication.data.model

data class MindSharePostDetailResponse(
    val postId : Long,
    val title : String,
    val content : String,
    val viewCount : Long,
    val commentCount : Long,
    val createdDate : String,
    val member : MemberResponse,
    val comments : List<MindSharePostCommentResponse>,
    val likes : List<MindSharePostLikeResponse>
)