package com.jydev.mindtravelapplication.data.model

import com.jydev.mindtravelapplication.domain.model.MindSharePostCategory

data class MindSharePostRequest(val title : String, val content : String, val category: MindSharePostCategory)