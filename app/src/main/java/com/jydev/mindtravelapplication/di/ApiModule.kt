package com.jydev.mindtravelapplication.di

import com.jydev.mindtravelapplication.data.api.MemberApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun loginApi(retrofit: Retrofit) : MemberApi {
        return retrofit.create(MemberApi::class.java)
    }
}