package com.jydev.mindtravelapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    val id : Long,
    val nickname : String,
    val profileImgUrl : String,
    val role : MemberRole
) : Parcelable
