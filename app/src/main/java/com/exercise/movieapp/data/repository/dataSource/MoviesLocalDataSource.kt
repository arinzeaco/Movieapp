package com.exercise.movieapp.data.repository.dataSource

import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesHide
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    suspend fun saveMoviesToDB(movies: List<Movies>)
    suspend fun saveMoviesToDB(movies: Movies)

    fun getSavedMovies(): Flow<List<Movies>>
    suspend fun deleteMoviesFromDB(movies: Movies)

    suspend fun saveMoviesHideToDB(moviesHide: MoviesHide)
    suspend fun deleteMoviesHideFromDB(moviesHide: MoviesHide)
     fun getSavedMoviesHide(): Flow<List<MoviesHide>>


}