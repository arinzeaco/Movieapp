package com.exercise.movieapp.data.db

import androidx.room.*
import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesFavorite
import com.exercise.movieapp.data.model.MoviesHide
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesFavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: MoviesFavorite)

    @Query("SELECT * FROM MoviesFavorite")
    fun getAllMoviesFavorite(): Flow<List<MoviesFavorite>>

    @Delete
    suspend fun deleteMoviesFavorite(moviesFavorite: MoviesFavorite)

}