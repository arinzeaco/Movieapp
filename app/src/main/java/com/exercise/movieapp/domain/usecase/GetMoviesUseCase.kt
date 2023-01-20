package com.exercise.movieapp.domain.usecase

import com.exercise.movieapp.data.model.APIResponse
import com.exercise.movieapp.data.util.Resource
import com.exercise.movieapp.domain.repository.MoviesRepository

class GetMoviesUseCase(private val MoviesRepository: MoviesRepository) {

    suspend fun execute(country : String, page : Int): Resource<APIResponse>{
        return MoviesRepository.getMovies(country,page)
    }
}