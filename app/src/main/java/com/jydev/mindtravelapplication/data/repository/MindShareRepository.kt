package com.jydev.mindtravelapplication.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jydev.mindtravelapplication.data.api.MindApi
import com.jydev.mindtravelapplication.data.mapper.toDomain
import com.jydev.mindtravelapplication.data.model.MindSharePostChildCommentRequest
import com.jydev.mindtravelapplication.data.model.MindSharePostLikeResponse
import com.jydev.mindtravelapplication.data.model.MindSharePostRequest
import com.jydev.mindtravelapplication.data.model.MindSharePostsRequest
import com.jydev.mindtravelapplication.data.network.TokenRefreshManager
import com.jydev.mindtravelapplication.data.network.getData
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import com.jydev.mindtravelapplication.domain.model.*
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

    suspend fun fetchMindSharePost(postId : Long) : MindSharePostDetail{
        return mindApi.fetchMindSharePostDetail(
            loginPreference.getToken()?.accessToken?:"",
            postId
        ).getData(tokenRefreshManager::refreshToken).toDomain()
    }

    suspend fun mindSharePostLike(postId : Long) : List<MindSharePostLike> {
        return mindApi.mindSharePostLike(
            loginPreference.getToken()?.accessToken?:"",
            postId
        ).getData(tokenRefreshManager::refreshToken).map {
            it.toDomain()
        }
    }

    suspend fun deleteMindSharePostLike(postId : Long) : List<MindSharePostLike> {
        return mindApi.deleteMindSharePostLike(
            loginPreference.getToken()?.accessToken?:"",
            postId
        ).getData(tokenRefreshManager::refreshToken).map {
            it.toDomain()
        }
    }

    fun fetchMindSharePosts(request: MindSharePostsRequest) : Flow<PagingData<MindSharePost>>{
        return Pager(
            PagingConfig(request.pageSize)
        ){
            MindSharePostPagingSource(request, mindApi,loginPreference,tokenRefreshManager)
        }.flow
    }

    suspend fun fetchPostLikes(postId : Long) : List<MindSharePostLike> {
        return mindApi.fetchMindSharePostLikes(
            loginPreference.getToken()?.accessToken?:"",
            postId
        ).getData(tokenRefreshManager::refreshToken).map {
            it.toDomain()
        }
    }

    suspend fun fetchPostComments(postId : Long) : List<MindSharePostComment> {
        return mindApi.fetchMindSharePostComments(
            loginPreference.getToken()?.accessToken?:"",
            postId
        ).getData(tokenRefreshManager::refreshToken).map {
            it.toDomain()
        }
    }

    suspend fun insertPostComment(content : String, postId: Long) : List<MindSharePostComment> {
        return mindApi.insertMindSharePostComment(
            loginPreference.getToken()?.accessToken?:"",
            postId,
            content
        ).getData(tokenRefreshManager::refreshToken).map {
            it.toDomain()
        }
    }

    suspend fun deletePostComment(postId: Long,commentId : Long) : List<MindSharePostComment> {
        return mindApi.deleteMindSharePostComment(
            loginPreference.getToken()?.accessToken?:"",
            postId,
            commentId
        ).getData(tokenRefreshManager::refreshToken).map {
            it.toDomain()
        }
    }

    suspend fun editPostComment(content : String, commentId : Long, postId : Long) : List<MindSharePostComment> {
        return mindApi.editMindSharePostComment(
            loginPreference.getToken()?.accessToken?:"",
            postId,
            commentId,
            content
        ).getData(tokenRefreshManager::refreshToken).map {
            it.toDomain()
        }
    }

    suspend fun insertPostChildComment(request : MindSharePostChildCommentRequest, postId : Long) : List<MindSharePostComment> {
        return mindApi.insertMindSharePostChildComment(
            loginPreference.getToken()?.accessToken?:"",
            postId,
            request
        ).getData(tokenRefreshManager::refreshToken).map {
            it.toDomain()
        }
    }

    suspend fun deletePostChildComment(postId: Long,commentId : Long) : List<MindSharePostComment> {
        return mindApi.deleteMindSharePostChildComment(
            loginPreference.getToken()?.accessToken?:"",
            postId,
            commentId
        ).getData(tokenRefreshManager::refreshToken).map {
            it.toDomain()
        }
    }

    suspend fun editPostChildComment(content : String, commentId : Long, postId : Long) : List<MindSharePostComment> {
        return mindApi.editMindSharePostChildComment(
            loginPreference.getToken()?.accessToken?:"",
            postId,
            commentId,
            content
        ).getData(tokenRefreshManager::refreshToken).map {
            it.toDomain()
        }
    }
}