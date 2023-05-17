package com.jydev.mindtravelapplication.data.api

import com.jydev.mindtravelapplication.data.model.HttpResponse
import com.jydev.mindtravelapplication.data.model.StoreItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface StoreApi {
    @GET("/v1/store/items")
    suspend fun fetchStoreItems(
        @Header("Authorization") accessToken: String,
    ): Response<HttpResponse<List<StoreItemResponse>>>
}