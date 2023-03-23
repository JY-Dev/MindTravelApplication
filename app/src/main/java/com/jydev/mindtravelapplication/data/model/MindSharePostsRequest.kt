package com.jydev.mindtravelapplication.data.model

import com.jydev.mindtravelapplication.domain.model.MindSharePostCategory

data class MindSharePostsRequest(
    val pageOffset : Long,
    val pageSize : Long,
    val category: MindSharePostCategory
)
