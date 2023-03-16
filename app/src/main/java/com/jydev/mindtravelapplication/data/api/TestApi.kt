package com.jydev.mindtravelapplication.data.api

import com.jydev.mindtravelapplication.data.model.HttpResponse
import com.jydev.mindtravelapplication.data.model.RecordRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TestApi {
    @POST("/test")
    suspend fun test(@Body recordRequest: RecordRequest) : Response<HttpResponse<Unit>>
}