package com.jydev.mindtravelapplication.data.api

import com.jydev.mindtravelapplication.data.model.HttpResponse
import com.jydev.mindtravelapplication.data.model.RecordRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MindApi {
    @POST("/v1/mind/travel/record-feeling")
    suspend fun recordFeeling(
        @Body recordRequest: RecordRequest
    ) : Response<HttpResponse<Unit>>
}