package com.jydev.mindtravelapplication.domain.model

import com.jydev.mindtravelapplication.domain.model.MemberRole

data class Member(
    val id : Long,
    val nickname : String,
    val profileImgUrl : String,
    val role : MemberRole
)
