package com.jydev.mindtravelapplication.data.model

import com.jydev.mindtravelapplication.domain.model.Mood

data class RecordRequest(val content : String, val mood : Mood)
