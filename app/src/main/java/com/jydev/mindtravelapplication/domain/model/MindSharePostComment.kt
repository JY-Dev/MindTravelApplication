package com.jydev.mindtravelapplication.domain.model

import java.time.LocalDateTime

data class MindSharePostComment(
    override val commentId : Long,
    override val content : String,
    override val member : Member,
    override val createdDate : LocalDateTime,
    val childComments : List<MindSharePostChildComment>
) : PostComment()
