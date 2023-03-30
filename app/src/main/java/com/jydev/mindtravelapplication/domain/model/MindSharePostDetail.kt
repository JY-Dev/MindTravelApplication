package com.jydev.mindtravelapplication.domain.model

import java.time.LocalDateTime

data class MindSharePostDetail(
    val postId : Long,
    val title : String,
    val content : String,
    val viewCount : Long,
    val commentCount : Long,
    val createdDate : LocalDateTime,
    val member : Member,
    val comments : List<MindSharePostComment>,
    val likes : List<MindSharePostLike>
)