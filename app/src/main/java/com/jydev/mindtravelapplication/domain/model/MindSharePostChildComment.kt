package com.jydev.mindtravelapplication.domain.model

import java.time.LocalDateTime

data class MindSharePostChildComment(
    override val commentId : Long,
    override val content : String,
    override val member : Member,
    val tagNickname : String,
    override val createdDate : LocalDateTime
) : PostComment()
