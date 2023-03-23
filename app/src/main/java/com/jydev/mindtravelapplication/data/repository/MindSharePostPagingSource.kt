package com.jydev.mindtravelapplication.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jydev.mindtravelapplication.data.api.MindApi
import com.jydev.mindtravelapplication.data.mapper.toDomain
import com.jydev.mindtravelapplication.data.model.MindSharePostsRequest
import com.jydev.mindtravelapplication.data.network.TokenRefreshManager
import com.jydev.mindtravelapplication.data.network.getData
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import com.jydev.mindtravelapplication.domain.model.MindSharePost

class MindSharePostPagingSource(
    private val request : MindSharePostsRequest,
    private val api: MindApi,
    private val loginPreference: LoginPreference,
    private val tokenRefreshManager: TokenRefreshManager
) : PagingSource<Long, MindSharePost>() {
    override fun getRefreshKey(state: PagingState<Long, MindSharePost>): Long? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.prevKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, MindSharePost> {
        val offset = params.key ?: 0L
        val accessToken = loginPreference.getToken()?.accessToken ?: ""
        val data = api.fetchMindSharePosts(accessToken,offset,request.pageSize,request.category).getData(tokenRefreshManager::refreshToken).toDomain().posts
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = if (data.isEmpty()) null else offset.plus(1)
        )
    }
}