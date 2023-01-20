package com.exercise.movieapp.domain.usecase

import com.exercise.movieapp.data.model.APIResponse
import com.exercise.movieapp.data.util.Resource
import com.exercise.movieapp.domain.repository.MoviesRepository

class GetMoviesUseCase(private val moviesRepository: MoviesRepository) {

    suspend fun execute(): Resource<APIResponse>{
        return moviesRepository.getMovies()
    }
}