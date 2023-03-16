package com.jydev.mindtravelapplication.data.repository

import com.jydev.mindtravelapplication.data.api.MindApi
import com.jydev.mindtravelapplication.data.model.RecordRequest
import com.jydev.mindtravelapplication.data.network.TokenRefreshManager
import com.jydev.mindtravelapplication.data.network.getData
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MindTravelRepository @Inject constructor(
    private val mindApi: MindApi,
    private val tokenRefreshManager: TokenRefreshManager,
    private val loginPreference: LoginPreference
) {
    suspend fun recordFeeling(recordRequest: RecordRequest) {
        val accessToken = loginPreference.getToken()?.accessToken?:""
        mindApi.recordFeeling(accessToken,recordRequest).getData(
            tokenRefreshManager::refreshToken
        )
    }
}