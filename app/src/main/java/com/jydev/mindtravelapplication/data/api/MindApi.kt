package com.jydev.mindtravelapplication.data.api

import com.jydev.mindtravelapplication.data.model.*
import com.jydev.mindtravelapplication.domain.model.MindSharePostCategory
import retrofit2.Response
import retrofit2.http.*

interface MindApi {
    @POST("/v1/mind/travel/record-mood")
    suspend fun recordMood(
        @Header("Authorization") accessToken: String,
        @Body recordRequest: RecordRequest
    ) : Response<HttpResponse<Unit>>

    @DELETE("/v1/mind/travel/record-mood/{moodRecordId}")
    suspend fun deleteRecordMood(
        @Header("Authorization") accessToken: String,
        @Path("moodRecordId") recordId : Long
    ) : Response<HttpResponse<Unit>>

    @GET("/v1/mind/travel/record-moods")
    suspend fun fetchRecordMoods(
        @Header("Authorization") accessToken: String,
        @Query("date") date : String
    ) : Response<HttpResponse<List<MoodRecordResponse>>>

    @POST("/v1/mind/share/post")
    suspend fun postMindSharePost(
        @Header("Authorization") accessToken: String,
        @Body mindSharePostRequest: MindSharePostRequest
    ) : Response<HttpResponse<Unit>>

    @GET("/v1/mind/share/post")
    suspend fun fetchMindSharePosts(
        @Header("Authorization") accessToken: String,
        @Query("pageOffset") pageOffset : Long,
        @Query("pageSize") pageSize : Int,
        @Query("category") category: MindSharePostCategory
    ) : Response<HttpResponse<MindSharePostsResponse>>

    @GET("/v1/mind/share/post/{postId}")
    suspend fun fetchMindSharePostDetail(
        @Header("Authorization") accessToken: String,
        @Path("postId") postId : Long
    ) : Response<HttpResponse<MindSharePostDetailResponse>>

    @POST("/v1/mind/share/post/{postId}/like")
    suspend fun mindSharePostLike(
        @Header("Authorization") accessToken: String,
        @Path("postId") postId : Long
    ) : Response<HttpResponse<List<MindSharePostLikeResponse>>>

    @DELETE("/v1/mind/share/post/{postId}/like")
    suspend fun deleteMindSharePostLike(
        @Header("Authorization") accessToken: String,
        @Path("postId") postId : Long
    ) : Response<HttpResponse<List<MindSharePostLikeResponse>>>
}