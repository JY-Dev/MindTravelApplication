package com.jydev.mindtravelapplication.data.model

import com.jydev.mindtravelapplication.domain.model.MindSharePostCategory

data class MindSharePostsRequest(
    val pageOffset : Long = 0L,
    val pageSize : Int = 10,
    val category: MindSharePostCategory
)
