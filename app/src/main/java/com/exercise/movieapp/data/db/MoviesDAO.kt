package com.exercise.movieapp.data.db

import androidx.room.*
import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesHide
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: Movies)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<Movies>)

    @Query("SELECT * FROM Movies WHERE title LIKE :search || '%' AND idd NOT IN (:idd)")
//    @Query("SELECT * FROM Movies WHERE idd NOT IN (:idd)")
   fun getAllMovies(search:String, idd:List<String>):
            Flow<List<Movies>>


    @Query("SELECT * FROM Movies")
    fun getAllMovies(): Flow<List<Movies>>



    @Query("DELETE FROM Movies")

    suspend fun deleteMovies()



}