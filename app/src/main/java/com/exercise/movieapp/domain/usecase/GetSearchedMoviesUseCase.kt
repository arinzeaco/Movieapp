package com.exercise.movieapp.domain.usecase

import com.exercise.movieapp.data.model.APIResponse
import com.exercise.movieapp.data.util.Resource
import com.exercise.movieapp.domain.repository.MoviesRepository

class GetSearchedMoviesUseCase(private val MoviesRepository: MoviesRepository) {
     suspend fun execute(searchQuery:String): Resource<APIResponse>{
         return MoviesRepository.getSearchedMovies(searchQuery)
     }
}