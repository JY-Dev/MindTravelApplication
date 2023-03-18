package com.jydev.mindtravelapplication.data.model

import com.jydev.mindtravelapplication.domain.model.Mood

data class MoodRecordResponse(val moodRecordId : Long, val content : String, val mood : Mood, val createdDate : String)