package com.exercise.movieapp.domain.usecase

import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesFavorite
import com.exercise.movieapp.domain.repository.MoviesRepository

class DeleteSavedMoviesUseCase(private val MoviesRepository: MoviesRepository) {
    suspend fun execute(movies: Movies)=MoviesRepository.deleteMovies(movies)
    suspend fun executeFavorite(moviesFavorite: MoviesFavorite)=MoviesRepository.deleteMoviesFavorite(moviesFavorite)
}