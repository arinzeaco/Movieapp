package com.exercise.movieapp.data.repository.dataSource

import com.exercise.movieapp.data.model.APIResponse
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getMovies(country : String, page : Int):Response<APIResponse>
    suspend fun getSearchedMovies(country : String,searchQuery:String, page : Int):Response<APIResponse>
}