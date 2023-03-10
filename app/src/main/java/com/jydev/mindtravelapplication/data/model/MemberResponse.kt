package com.jydev.mindtravelapplication.data.model

import com.jydev.mindtravelapplication.domain.model.MemberRole

data class MemberResponse(val email : String, val nickname : String , val profileImgUrl : String, val role : MemberRole)
