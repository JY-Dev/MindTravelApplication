package com.jydev.mindtravelapplication.data.model

data class MindSharePostChildCommentRequest(val content : String, val tagMemberId : Long, val parentCommentId : Long)