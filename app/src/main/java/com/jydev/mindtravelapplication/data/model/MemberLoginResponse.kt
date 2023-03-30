package com.jydev.mindtravelapplication.data.model

import com.jydev.mindtravelapplication.domain.model.MemberRole

data class MemberLoginResponse(
    val memberIdx: Long,
    val email: String,
    val nickname: String,
    val createdDate: String,
    val profileImgUrl: String,
    val role: MemberRole
)
