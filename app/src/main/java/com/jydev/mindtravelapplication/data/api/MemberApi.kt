package com.jydev.mindtravelapplication.data.api

import com.jydev.mindtravelapplication.data.model.HttpResponse
import com.jydev.mindtravelapplication.data.model.MemberResponse
import com.jydev.mindtravelapplication.data.model.TokenResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface MemberApi {
    @POST("/login/oauth2/{socialLoginType}")
    suspend fun socialLogin(
        @Path("socialLoginType") socialLoginType: String,
        @Header("Authorization") accessToken: String
    ) : HttpResponse<TokenResponse>

    @GET("/member")
    suspend fun getMember(
        @Header("Authorization") accessToken: String
    ) : HttpResponse<MemberResponse>
}