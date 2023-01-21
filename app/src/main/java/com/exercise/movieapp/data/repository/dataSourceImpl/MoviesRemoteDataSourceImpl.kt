package com.exercise.movieapp.data.repository.dataSourceImpl

import com.exercise.movieapp.data.api.MoviesAPIService
import com.exercise.movieapp.data.model.APIResponse
import com.exercise.movieapp.data.repository.dataSource.MoviesRemoteDataSource
import retrofit2.Response

class MoviesRemoteDataSourceImpl(
        private val moviesAPIService: MoviesAPIService
):MoviesRemoteDataSource {
    override suspend fun getMovies(): Response<APIResponse> {
          return moviesAPIService.getMovies()
    }

//    override suspend fun getSearchedMovies(
//        searchQuery: String,
//    ): Response<APIResponse> {
//        return moviesAPIService.getSearchedMovies(searchQuery)
//    }
}