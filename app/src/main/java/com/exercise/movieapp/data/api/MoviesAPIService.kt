package com.exercise.movieapp.data.api

import com.exercise.movieapp.BuildConfig
import com.exercise.movieapp.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPIService {
  @GET("trending/all/week")
  suspend fun getMovies(
      @Query("api_key")
      apiKey:String = BuildConfig.API_KEY
  ): Response<APIResponse>

    @GET("search/movie")
    suspend fun getSearchedMovies(
        @Query("query")
        searchQuery:String,
        @Query("api_key")
        apiKey:String = BuildConfig.API_KEY
    ): Response<APIResponse>

}