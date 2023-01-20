package com.exercise.movieapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesFavorite
import com.exercise.movieapp.data.model.MoviesHide

@Database(
    entities = [Movies::class,MoviesHide::class, MoviesFavorite::class],
    version = 2,
    exportSchema = false
)
abstract  class MoviesDatabase : RoomDatabase(){
    abstract fun getMoviesDAO():MoviesDAO
    abstract fun getMoviesHideDAO():MoviesHideDAO
    abstract fun getMoviesFavoriteDAO():MoviesFavoriteDAO
}

