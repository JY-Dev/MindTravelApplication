package com.jydev.mindtravelapplication.data.network

import com.jydev.mindtravelapplication.data.api.AuthApi
import com.jydev.mindtravelapplication.data.mapper.toDomain
import com.jydev.mindtravelapplication.data.network.exception.RefreshTokenExpiredException
import com.jydev.mindtravelapplication.data.network.exception.RetryRequestException
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRefreshManager @Inject constructor(private val authApi: AuthApi, private val loginPreference: LoginPreference) {
    suspend fun refreshToken() : Nothing {
        val refreshToken = loginPreference.getToken()!!.refreshToken
        val token = authApi.reissueToken(refreshToken).getData(null,::tokenExpired).toDomain()
        loginPreference.saveToken(token)
        throw RetryRequestException()
    }

    fun tokenExpired() : Nothing{
        loginPreference.clearData()
        throw RefreshTokenExpiredException()
    }
}