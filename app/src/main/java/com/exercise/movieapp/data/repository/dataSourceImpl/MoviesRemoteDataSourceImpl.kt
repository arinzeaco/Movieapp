package com.exercise.movieapp.data.repository.dataSourceImpl

import com.exercise.movieapp.data.api.MoviesAPIService
import com.exercise.movieapp.data.model.APIResponse
import com.exercise.movieapp.data.repository.dataSource.MoviesRemoteDataSource
import retrofit2.Response

class MoviesRemoteDataSourceImpl(
        private val MoviesAPIService: MoviesAPIService
):MoviesRemoteDataSource {
    override suspend fun getMovies(country : String, page : Int): Response<APIResponse> {
          return MoviesAPIService.getMovies()
    }

    override suspend fun getSearchedMovies(
        country: String,
        searchQuery: String,
        page: Int
    ): Response<APIResponse> {
        return MoviesAPIService.getSearchedMovies(searchQuery)
    }
}