package com.jydev.mindtravelapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MindSharePostCategory(val text : String) : Parcelable {
    TROUBLE_COUNSELING("고민상담"),DAILY("일상")
}