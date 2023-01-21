package com.exercise.movieapp.data.repository.dataSource

import com.exercise.movieapp.data.model.APIResponse
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getMovies():Response<APIResponse>
//    suspend fun getSearchedMovies(searchQuery:String):Response<APIResponse>
}