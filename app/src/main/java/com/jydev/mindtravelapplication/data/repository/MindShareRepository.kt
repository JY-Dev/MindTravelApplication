package com.jydev.mindtravelapplication.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jydev.mindtravelapplication.data.api.MindApi
import com.jydev.mindtravelapplication.data.mapper.toDomain
import com.jydev.mindtravelapplication.data.model.MindSharePostRequest
import com.jydev.mindtravelapplication.data.model.MindSharePostsRequest
import com.jydev.mindtravelapplication.data.network.TokenRefreshManager
import com.jydev.mindtravelapplication.data.network.getData
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import com.jydev.mindtravelapplication.domain.model.MindSharePost
import com.jydev.mindtravelapplication.domain.model.MindSharePosts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.min

@Singleton
class MindShareRepository @Inject constructor(
    private val mindApi: MindApi,
    private val tokenRefreshManager: TokenRefreshManager,
    private val loginPreference: LoginPreference,
) {
    suspend fun postMindSharePost(request : MindSharePostRequest){
        mindApi.postMindSharePost(
            loginPreference.getToken()?.accessToken?:"",
            request
        ).getData(tokenRefreshManager::refreshToken)
    }

    fun fetchMindSharePosts(request: MindSharePostsRequest) : Flow<PagingData<MindSharePost>>{
        return Pager(
            PagingConfig(request.pageSize)
        ){
            MindSharePostPagingSource(request, mindApi,loginPreference,tokenRefreshManager)
        }.flow
    }
}