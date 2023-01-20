package com.exercise.movieapp.domain.usecase

import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesFavorite
import com.exercise.movieapp.domain.repository.MoviesRepository

class SaveMoviesUseCase(private val MoviesRepository: MoviesRepository) {
  suspend fun execute(movies: Movies)=MoviesRepository.saveMoviesToDB(movies)

  suspend fun execute(movies: List<Movies>)=MoviesRepository.saveMoviesToDB(movies)

  suspend fun executesaveFavorite(movies:MoviesFavorite)=MoviesRepository.saveFavoriteMoviesToDB(movies)
}