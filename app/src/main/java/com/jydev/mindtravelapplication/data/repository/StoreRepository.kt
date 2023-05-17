package com.jydev.mindtravelapplication.data.repository

import com.jydev.mindtravelapplication.data.api.StoreApi
import com.jydev.mindtravelapplication.data.mapper.toDomain
import com.jydev.mindtravelapplication.data.network.TokenRefreshManager
import com.jydev.mindtravelapplication.data.network.getData
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import com.jydev.mindtravelapplication.domain.model.StoreItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepository @Inject constructor(
    private val storeApi: StoreApi,
    private val tokenRefreshManager: TokenRefreshManager,
    private val loginPreference: LoginPreference
) {
    suspend fun fetchStoreItems(): List<StoreItem> {
        val accessToken = loginPreference.getToken()?.accessToken ?: ""
        return storeApi.fetchStoreItems(accessToken)
            .getData(tokenRefreshManager::refreshToken)
            .map {
                it.toDomain()
            }
    }

}