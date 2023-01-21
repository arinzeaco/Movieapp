package com.exercise.movieapp.data.repository.dataSource

import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesFavorite
import com.exercise.movieapp.data.model.MoviesHide
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {

    suspend fun saveFavoriteMoviesToDB(movies: MoviesFavorite)
    suspend fun deleteMoviesFavoriteFromDB(moviesFavorite: MoviesFavorite)
    fun getSavedMoviesFavorite(): Flow<List<MoviesFavorite>>


    suspend fun saveMoviesToDB(movies: List<Movies>)
    suspend fun saveMoviesToDB(movies: Movies)

    fun getSavedMovies(search:String,ids: List<String>): Flow<List<Movies>>
    fun getSavedMovies(): Flow<List<Movies>>
    suspend fun deleteMoviesFromDB()


    suspend fun saveMoviesHideToDB(moviesHide: MoviesHide)
    suspend fun deleteMoviesHideFromDB(moviesHide: MoviesHide)
    fun getSavedMoviesHide(): Flow<List<MoviesHide>>


}