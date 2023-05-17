package com.jydev.mindtravelapplication.di

import com.jydev.mindtravelapplication.data.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun loginApi(retrofit: Retrofit) : MemberApi {
        return retrofit.create(MemberApi::class.java)
    }

    @Provides
    @Singleton
    fun authApi(retrofit: Retrofit) : AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun mindApi(retrofit: Retrofit) : MindApi {
        return retrofit.create(MindApi::class.java)
    }

    @Provides
    @Singleton
    fun testApi(retrofit: Retrofit) : TestApi {
        return retrofit.create(TestApi::class.java)
    }

    @Provides
    @Singleton
    fun storeApi(retrofit: Retrofit) : StoreApi {
        return retrofit.create(StoreApi::class.java)
    }
}