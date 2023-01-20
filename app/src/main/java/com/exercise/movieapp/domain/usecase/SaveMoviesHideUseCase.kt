package com.exercise.movieapp.domain.usecase

import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesHide
import com.exercise.movieapp.domain.repository.MoviesRepository

class SaveMoviesHideUseCase(private val MoviesRepository: MoviesRepository) {
  suspend fun execute(moviesHide: MoviesHide)=MoviesRepository.saveMoviesHide(moviesHide)
}