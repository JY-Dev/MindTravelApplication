package com.jydev.mindtravelapplication.data.mapper

import com.jydev.mindtravelapplication.data.model.MemberResponse
import com.jydev.mindtravelapplication.data.model.TokenResponse
import com.jydev.mindtravelapplication.domain.model.Member
import com.jydev.mindtravelapplication.domain.model.Token

fun MemberResponse.toDomain() : Member{
    return Member(memberIdx,email, nickname, profileImgUrl,createdDate, role)
}

fun TokenResponse.toDomain() : Token{
    return Token("Bearer $accessToken", "Bearer $refreshToken")
}