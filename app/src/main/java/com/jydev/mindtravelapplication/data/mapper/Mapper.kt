package com.jydev.mindtravelapplication.data.mapper

import com.jydev.mindtravelapplication.data.model.*
import com.jydev.mindtravelapplication.domain.model.*
import java.time.LocalDateTime

fun MemberLoginResponse.toDomain(): MemberLogin {
    return MemberLogin(memberIdx, email, nickname, profileImgUrl, createdDate, role)
}

fun TokenResponse.toDomain(): Token {
    return Token("Bearer $accessToken", "Bearer $refreshToken")
}

fun MoodRecordResponse.toDomain(): MoodRecord {
    return MoodRecord(moodRecordId, content, mood, LocalDateTime.parse(createdDate))
}

fun MindSharePostResponse.toDomain(): MindSharePost {
    return MindSharePost(
        postId,
        member.toDomain(),
        title,
        category,
        likeCount,
        viewCount,
        commentCount,
        LocalDateTime.parse(createdDate)
    )
}

fun MindSharePostsResponse.toDomain(): MindSharePosts {
    return MindSharePosts(posts.map { it.toDomain() }, totalSize)
}

fun MemberResponse.toDomain(): Member {
    return Member(id, nickname, profileImgUrl, role)
}

fun MindSharePostDetailResponse.toDomain(): MindSharePostDetail {
    return MindSharePostDetail(
        postId,
        title,
        content,
        category,
        viewCount,
        commentCount,
        LocalDateTime.parse(createdDate),
        member.toDomain(),
        comments.map {
            it.toDomain()
        },
        likes.map {
            it.toDomain()
        }
    )
}

fun MindSharePostCommentResponse.toDomain() : MindSharePostComment {
    return MindSharePostComment(commentId,content, member.toDomain(),isDeleted, LocalDateTime.parse(createdDate),childComments.map {
        it.toDomain()
    })
}

fun MindSharePostChildCommentResponse.toDomain() : MindSharePostChildComment {
    return MindSharePostChildComment(commentId,content,member.toDomain(),parentCommentId,tagNickname, LocalDateTime.parse(createdDate))
}

fun MindSharePostLikeResponse.toDomain(): MindSharePostLike {
    return MindSharePostLike(postId, member.toDomain(), LocalDateTime.parse(createdDate))
}

fun StoreItemResponse.toDomain() : StoreItem {
    return StoreItem(price,quantity,item.toDomain())
}

fun ItemResponse.toDomain() : Item {
    return Item(id, type)
}