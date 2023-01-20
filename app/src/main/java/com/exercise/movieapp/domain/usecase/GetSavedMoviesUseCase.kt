package com.exercise.movieapp.domain.usecase

import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesFavorite
import com.exercise.movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetSavedMoviesUseCase(private val moviesRepository: MoviesRepository) {
    fun execute(): Flow<List<Movies>>{
        return moviesRepository.getSavedMovies()
    }

    fun executeFavorite(): Flow<List<MoviesFavorite>>{
        return moviesRepository.getSavedMoviesFavorite()
    }

}