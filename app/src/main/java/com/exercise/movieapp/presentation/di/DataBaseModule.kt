package com.exercise.movieapp.presentation.di

import android.app.Application
import androidx.room.Room
import com.exercise.movieapp.data.db.MoviesDAO
import com.exercise.movieapp.data.db.MoviesDatabase
import com.exercise.movieapp.data.db.MoviesFavoriteDAO
import com.exercise.movieapp.data.db.MoviesHideDAO
//import com.exercise.movieapp.data.db.MoviesHideDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideMoviesDatabase(app: Application): MoviesDatabase {
        return Room.databaseBuilder(app, MoviesDatabase::class.java, "Movies_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMoviesDao(moviesDatabase: MoviesDatabase): MoviesDAO {
        return moviesDatabase.getMoviesDAO()
    }

    @Singleton
    @Provides
    fun provideMoviesHideDao(moviesDatabase: MoviesDatabase): MoviesHideDAO {
        return moviesDatabase.getMoviesHideDAO()
    }

    @Singleton
    @Provides
    fun provideMoviesFavoriteDao(moviesDatabase: MoviesDatabase): MoviesFavoriteDAO {
        return moviesDatabase.getMoviesFavoriteDAO()
    }


}