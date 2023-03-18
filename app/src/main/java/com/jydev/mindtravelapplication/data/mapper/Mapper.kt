package com.jydev.mindtravelapplication.data.mapper

import com.jydev.mindtravelapplication.data.model.MemberResponse
import com.jydev.mindtravelapplication.data.model.MoodRecordResponse
import com.jydev.mindtravelapplication.data.model.TokenResponse
import com.jydev.mindtravelapplication.domain.model.Member
import com.jydev.mindtravelapplication.domain.model.MoodRecord
import com.jydev.mindtravelapplication.domain.model.Token
import java.time.LocalDateTime

fun MemberResponse.toDomain() : Member{
    return Member(memberIdx,email, nickname, profileImgUrl,createdDate, role)
}

fun TokenResponse.toDomain() : Token{
    return Token("Bearer $accessToken", "Bearer $refreshToken")
}

fun MoodRecordResponse.toDomain() : MoodRecord{
    return MoodRecord(content, mood, LocalDateTime.parse(createdDate))
}