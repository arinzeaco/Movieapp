package com.exercise.movieapp.data.db

import androidx.room.*
import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesHide
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesHideDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(Movies: MoviesHide)

    @Query("SELECT * FROM MoviesHide")
    fun getAllMoviesHide(): Flow<List<MoviesHide>>

    @Delete
    suspend fun deleteMoviesHide(moviesHide: MoviesHide)



}