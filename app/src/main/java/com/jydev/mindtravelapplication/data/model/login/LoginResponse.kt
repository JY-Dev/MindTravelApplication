package com.jydev.mindtravelapplication.data.model.login

import com.jydev.mindtravelapplication.data.model.MemberResponse
import com.jydev.mindtravelapplication.data.model.TokenResponse

data class LoginResponse(val isNewMember : Boolean, val memberResponse: MemberResponse, val tokenResponse : TokenResponse)