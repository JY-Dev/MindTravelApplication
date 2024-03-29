package com.jydev.mindtravelapplication.data.repository

import com.jydev.mindtravelapplication.data.api.MemberApi
import com.jydev.mindtravelapplication.data.mapper.toDomain
import com.jydev.mindtravelapplication.data.network.TokenRefreshManager
import com.jydev.mindtravelapplication.data.network.getData
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import com.jydev.mindtravelapplication.domain.model.MemberLogin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemberRepository @Inject constructor(
    private val memberApi: MemberApi,
    private val tokenRefreshManager: TokenRefreshManager,
    private val loginPreference: LoginPreference
) {
    suspend fun getMember(): MemberLogin {
        return memberApi.getMember(loginPreference.getToken()?.accessToken ?: "")
            .getData(
                tokenRefreshManager::refreshToken,
                tokenRefreshManager::tokenExpired
            ).toDomain()
    }

    suspend fun editNickname(nickname : String) : MemberLogin {
        val member = memberApi.editNickname(loginPreference.getToken()?.accessToken ?: "",nickname)
            .getData(
                tokenRefreshManager::refreshToken
            ).toDomain()
        loginPreference.saveMember(member)
        return member
    }
}