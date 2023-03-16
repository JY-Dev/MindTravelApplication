package com.jydev.mindtravelapplication.data.repository

import com.jydev.mindtravelapplication.data.api.MindApi
import com.jydev.mindtravelapplication.data.model.RecordRequest
import com.jydev.mindtravelapplication.data.network.TokenRefreshManager
import com.jydev.mindtravelapplication.data.network.getData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MindTravelRepository @Inject constructor(
    private val mindApi: MindApi,
    private val tokenRefreshManager: TokenRefreshManager
) {
    suspend fun recordFeeling(recordRequest: RecordRequest) {
        mindApi.recordFeeling(recordRequest).getData(tokenRefreshManager::refreshToken)
    }
}