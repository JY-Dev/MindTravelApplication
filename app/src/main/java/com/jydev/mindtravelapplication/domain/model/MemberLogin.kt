package com.jydev.mindtravelapplication.domain.model

data class MemberLogin(
    val memberIdx: Long,
    val email: String,
    val nickname: String,
    val profileImgUrl: String,
    val createdDate : String,
    val role: MemberRole
)
