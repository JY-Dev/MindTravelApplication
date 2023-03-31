package com.jydev.mindtravelapplication.domain.model

import java.time.LocalDateTime

sealed class PostComment{
    abstract val commentId: Long
    abstract val content : String
    abstract val member : Member
    abstract val createdDate : LocalDateTime
}
