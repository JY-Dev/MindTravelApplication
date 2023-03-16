package com.jydev.mindtravelapplication.data.repository

import com.jydev.mindtravelapplication.data.api.TestApi
import com.jydev.mindtravelapplication.data.model.RecordRequest
import com.jydev.mindtravelapplication.data.network.TokenRefreshManager
import com.jydev.mindtravelapplication.data.network.getData
import javax.inject.Inject

class TestRepository@Inject constructor(
    private val testApi: TestApi,
    private val tokenRefreshManager: TokenRefreshManager
) {
    suspend fun test(recordRequest: RecordRequest){
        testApi.test(recordRequest).getData(tokenRefreshManager::refreshToken)
    }
}