package com.jydev.mindtravelapplication.data.mapper

import com.jydev.mindtravelapplication.data.model.*
import com.jydev.mindtravelapplication.domain.model.*
import java.time.LocalDateTime

fun MemberResponse.toDomain() : Member{
    return Member(memberIdx,email, nickname, profileImgUrl,createdDate, role)
}

fun TokenResponse.toDomain() : Token{
    return Token("Bearer $accessToken", "Bearer $refreshToken")
}

fun MoodRecordResponse.toDomain() : MoodRecord{
    return MoodRecord(moodRecordId,content, mood, LocalDateTime.parse(createdDate))
}

fun MindSharePostResponse.toDomain() : MindSharePost{
    return MindSharePost(postId,nickname,title,likeCount,viewCount,LocalDateTime.parse(createdDate))
}

fun MindSharePostsResponse.toDomain() : MindSharePosts{
    return MindSharePosts(posts.map { it.toDomain() },totalSize)
}