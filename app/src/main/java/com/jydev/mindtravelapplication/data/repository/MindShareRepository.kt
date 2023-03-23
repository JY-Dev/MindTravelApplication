package com.jydev.mindtravelapplication.data.repository

import com.jydev.mindtravelapplication.data.api.MindApi
import com.jydev.mindtravelapplication.data.model.MindSharePostRequest
import com.jydev.mindtravelapplication.data.network.TokenRefreshManager
import com.jydev.mindtravelapplication.data.network.getData
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MindShareRepository @Inject constructor(
    private val mindApi: MindApi,
    private val tokenRefreshManager: TokenRefreshManager,
    private val loginPreference: LoginPreference
) {
    suspend fun postMindSharePost(request : MindSharePostRequest){
        mindApi.postMindSharePost(
            loginPreference.getToken()?.accessToken?:"",
            request
        ).getData(tokenRefreshManager::refreshToken)
    }
}