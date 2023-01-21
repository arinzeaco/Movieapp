package com.exercise.movieapp.presentation.di

import com.exercise.movieapp.BuildConfig
import com.exercise.movieapp.data.api.MoviesAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
         return Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .baseUrl(BuildConfig.BASE_URL)
             .client(client)
             .build()
    }

    @Singleton
    @Provides
    fun provideMoviesAPIService(retrofit: Retrofit):MoviesAPIService{
        return retrofit.create(MoviesAPIService::class.java)
    }
    val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(25,TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor(getHeaderInterceptor())
    }.build()
    private fun getHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            request = request.newBuilder()
                .addHeader("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2MTNlNTUzMmNiMWM4YTM2NDBiNjUxNjIiLCJpYXQiOjE2NjM2ODY1MDYsImV4cCI6MTY2NDI5MTMwNn0.sCn_k17QjRPlqFxDcVLlITK3_4zUZHTocvraIo1h1fU")

//                    .addHeader("Authorzization", "")
                .addHeader("Content-type", "application/json")
                .build()

            chain.proceed(request)
        }
    }

}













