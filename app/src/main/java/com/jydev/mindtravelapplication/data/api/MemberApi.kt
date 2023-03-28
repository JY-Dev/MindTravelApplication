package com.jydev.mindtravelapplication.data.api

import com.jydev.mindtravelapplication.data.model.HttpResponse
import com.jydev.mindtravelapplication.data.model.MemberResponse
import com.jydev.mindtravelapplication.data.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface MemberApi {


    @GET("/v1/member")
    suspend fun getMember(
        @Header("Authorization") accessToken: String
    ) : Response<HttpResponse<MemberResponse>>

    @PATCH("/v1/member/{nickname}")
    suspend fun editNickname(
        @Header("Authorization") accessToken: String,
        @Path("nickname") nickname : String
    ) : Response<HttpResponse<MemberResponse>>
}