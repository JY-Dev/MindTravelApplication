package com.jydev.mindtravelapplication.data.repository

import com.jydev.mindtravelapplication.data.api.AuthApi
import com.jydev.mindtravelapplication.data.api.MemberApi
import com.jydev.mindtravelapplication.data.mapper.toDomain
import com.jydev.mindtravelapplication.data.model.login.SocialLoginRequest
import com.jydev.mindtravelapplication.data.network.getData
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import com.jydev.mindtravelapplication.domain.model.Member
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val memberApi: MemberApi,
    private val authApi: AuthApi,
    private val loginPreference: LoginPreference
) {
    suspend fun socialLogin(socialLoginRequest: SocialLoginRequest): Member {
        val token = authApi.socialLogin(
            socialLoginRequest.socialLoginType.socialType,
            "Bearer " + socialLoginRequest.accessToken
        ).getData().toDomain()
        val member = memberApi.getMember(token.accessToken).getData().toDomain()
        loginPreference.saveToken(token)
        loginPreference.saveMember(member)
        return member
    }
}