package com.jydev.mindtravelapplication.domain.model

data class Member(val memberIdx : Long,val email : String, val nickname : String , val profileImgUrl : String, val role : MemberRole)
