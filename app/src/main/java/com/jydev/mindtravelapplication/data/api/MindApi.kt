package com.jydev.mindtravelapplication.data.api

import com.jydev.mindtravelapplication.data.model.HttpResponse
import com.jydev.mindtravelapplication.data.model.MoodRecordResponse
import com.jydev.mindtravelapplication.data.model.RecordRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface MindApi {
    @POST("/v1/mind/travel/record-mood")
    suspend fun recordMood(
        @Header("Authorization") accessToken: String,
        @Body recordRequest: RecordRequest
    ) : Response<HttpResponse<Unit>>

    @GET("/v1/mind/travel/record-moods")
    suspend fun fetchRecordMoods(
        @Header("Authorization") accessToken: String,
        @Query("date") date : String
    ) : Response<HttpResponse<List<MoodRecordResponse>>>
}