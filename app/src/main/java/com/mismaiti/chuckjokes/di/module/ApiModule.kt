package com.mismaiti.chuckjokes.di.module

import com.mismaiti.chuckjokes.BuildConfig
import com.mismaiti.chuckjokes.api.JokesApi
import com.mismaiti.chuckjokes.api.JokesService
import dagger.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.connectTimeout(20, TimeUnit.SECONDS)
        client.readTimeout(20,TimeUnit.SECONDS)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): JokesApi =
        retrofit.create(JokesApi::class.java)

    @Provides
    @Singleton
    fun provideMovieService(movieApi: JokesApi) = JokesService(movieApi)
}
