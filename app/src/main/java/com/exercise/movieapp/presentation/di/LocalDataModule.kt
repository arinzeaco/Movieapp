package com.exercise.movieapp.presentation.di

import com.exercise.movieapp.data.db.MoviesDAO
import com.exercise.movieapp.data.db.MoviesFavoriteDAO
import com.exercise.movieapp.data.db.MoviesHideDAO
import com.exercise.movieapp.data.repository.dataSource.MoviesLocalDataSource
import com.exercise.movieapp.data.repository.dataSourceImpl.MoviesLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(moviesDAO: MoviesDAO,moviesHideDAO: MoviesHideDAO,moviesFavoriteDAO: MoviesFavoriteDAO):MoviesLocalDataSource{
      return MoviesLocalDataSourceImpl(moviesDAO,moviesHideDAO,moviesFavoriteDAO)
    }

}













