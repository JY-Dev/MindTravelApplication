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

    @GET("/v1/mind/share/post/{postId}/comments")
    suspend fun fetchMindSharePostComments(
        @Header("Authorization") accessToken: String,
        @Path("postId") postId : Long
    ) : Response<HttpResponse<List<MindSharePostCommentResponse>>>

    @GET("/v1/mind/share/post/{postId}/likes")
    suspend fun fetchMindSharePostLikes(
        @Header("Authorization") accessToken: String,
        @Path("postId") postId : Long
    ) : Response<HttpResponse<List<MindSharePostLikeResponse>>>

    @FormUrlEncoded
    @POST("/v1/mind/share/post/{postId}/comment")
    suspend fun insertMindSharePostComment(
        @Header("Authorization") accessToken: String,
        @Path("postId") postId : Long,
        @Field("content") content : String
    ) : Response<HttpResponse<List<MindSharePostCommentResponse>>>

    @DELETE("/v1/mind/share/post/{postId}/comment/{commentId}")
    suspend fun deleteMindSharePostComment(
        @Header("Authorization") accessToken: String,
        @Path("postId") postId : Long,
        @Path("commentId") commentId : Long
    ) : Response<HttpResponse<List<MindSharePostCommentResponse>>>

    @FormUrlEncoded
    @PATCH("/v1/mind/share/post/{postId}/comment/{commentId}")
    suspend fun editMindSharePostComment(
        @Header("Authorization") accessToken: String,
        @Path("postId") postId : Long,
        @Path("commentId") commentId : Long,
        @Field("content") content: String
    ) : Response<HttpResponse<List<MindSharePostCommentResponse>>>

    @POST("/v1/mind/share/post/{postId}/comment/child")
    suspend fun insertMindSharePostChildComment(
        @Header("Authorization") accessToken: String,
        @Path("postId") postId : Long,
        @Body request : MindSharePostChildCommentRequest
    ) : Response<HttpResponse<List<MindSharePostCommentResponse>>>

    @DELETE("/v1/mind/share/post/{postId}/comment/child/{commentId}")
    suspend fun deleteMindSharePostChildComment(
        @Header("Authorization") accessToken: String,
        @Path("postId") postId : Long,
        @Path("commentId") commentId : Long
    ) : Response<HttpResponse<List<MindSharePostCommentResponse>>>

    @FormUrlEncoded
    @PATCH("/v1/mind/share/post/{postId}/comment/child/{commentId}")
    suspend fun editMindSharePostChildComment(
        @Header("Authorization") accessToken: String,
        @Path("postId") postId : Long,
        @Path("commentId") commentId : Long,
        @Field("content") content: String
    ) : Response<HttpResponse<List<MindSharePostCommentResponse>>>
}