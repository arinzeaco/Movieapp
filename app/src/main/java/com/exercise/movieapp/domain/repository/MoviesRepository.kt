package com.exercise.movieapp.domain.repository

import com.exercise.movieapp.data.model.APIResponse
import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesFavorite
import com.exercise.movieapp.data.model.MoviesHide
import com.exercise.movieapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository{

      suspend fun getMovies(): Resource<APIResponse>
      suspend fun getSearchedMovies(searchQuery:String) : Resource<APIResponse>

      suspend fun saveMoviesToDB(movies: Movies)
      suspend fun saveMoviesToDB(movies: List<Movies>)
      suspend fun saveFavoriteMoviesToDB(moviesFavorite: MoviesFavorite)

      suspend fun deleteMovies(movies: Movies)
      suspend fun deleteMoviesFavorite(moviesFavorite: MoviesFavorite)



      fun getSavedMovies(ids:List<Int>): Flow<List<Movies>>
      fun getSavedMovies(): Flow<List<Movies>>
      fun getSavedMoviesFavorite(): Flow<List<MoviesFavorite>>



      suspend fun saveMoviesHide(moviesHide: MoviesHide)
      suspend fun deleteMoviesHide(moviesHide: MoviesHide)
      fun getSavedMoviesHide(): Flow<List<MoviesHide>>




}