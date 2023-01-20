package com.exercise.movieapp.domain.usecase

import com.exercise.movieapp.data.model.APIResponse
import com.exercise.movieapp.data.util.Resource
import com.exercise.movieapp.domain.repository.MoviesRepository

class GetSearchedMoviesUseCase(private val MoviesRepository: MoviesRepository) {
     suspend fun execute(country:String,searchQuery:String,page:Int): Resource<APIResponse>{
         return MoviesRepository.getSearchedMovies(country,searchQuery,page)
     }
}