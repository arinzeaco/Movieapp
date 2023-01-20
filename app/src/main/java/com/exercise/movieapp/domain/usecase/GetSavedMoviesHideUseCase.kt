package com.exercise.movieapp.domain.usecase

import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesHide
import com.exercise.movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetSavedMoviesHideUseCase(private val MoviesRepository: MoviesRepository) {
    fun execute(): Flow<List<MoviesHide>>{
        return MoviesRepository.getSavedMoviesHide()
    }
}