package com.jydev.mindtravelapplication.data.repository

import com.jydev.mindtravelapplication.data.api.MindApi
import com.jydev.mindtravelapplication.data.mapper.toDomain
import com.jydev.mindtravelapplication.data.model.RecordRequest
import com.jydev.mindtravelapplication.data.network.TokenRefreshManager
import com.jydev.mindtravelapplication.data.network.getData
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import com.jydev.mindtravelapplication.domain.model.MoodRecord
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MindTravelRepository @Inject constructor(
    private val mindApi: MindApi,
    private val tokenRefreshManager: TokenRefreshManager,
    private val loginPreference: LoginPreference
) {
    suspend fun recordMood(recordRequest: RecordRequest) {
        val accessToken = loginPreference.getToken()?.accessToken ?: ""
        mindApi.recordMood(accessToken, recordRequest).getData(
            tokenRefreshManager::refreshToken
        )
    }

    suspend fun fetchRecordList(date: String): List<MoodRecord> {
        val accessToken = loginPreference.getToken()?.accessToken ?: ""
        return mindApi.fetchRecordMoods(
            accessToken, date
        ).getData(
            tokenRefreshManager::refreshToken
        ).map {
            it.toDomain()
        }
    }
}