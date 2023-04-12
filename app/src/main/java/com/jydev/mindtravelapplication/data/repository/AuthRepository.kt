package com.jydev.mindtravelapplication.data.repository

import com.jydev.mindtravelapplication.data.api.AuthApi
import com.jydev.mindtravelapplication.data.mapper.toDomain
import com.jydev.mindtravelapplication.data.network.getData
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val authApi: AuthApi, private val loginPreference: LoginPreference) {
    suspend fun reissueToken(){
        val refreshToken = loginPreference.getToken()?.refreshToken?:""
        val token = authApi.reissueToken(refreshToken).getData().toDomain()
        loginPreference.saveToken(token)
    }
}