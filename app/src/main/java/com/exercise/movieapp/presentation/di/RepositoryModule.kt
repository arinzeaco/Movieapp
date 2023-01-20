package com.exercise.movieapp.presentation.di

import com.exercise.movieapp.data.repository.MoviesRepositoryImpl
import com.exercise.movieapp.data.repository.dataSource.MoviesLocalDataSource
import com.exercise.movieapp.data.repository.dataSource.MoviesRemoteDataSource
import com.exercise.movieapp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource,
        moviesLocalDataSource: MoviesLocalDataSource
    ): MoviesRepository {
        return MoviesRepositoryImpl(
            moviesRemoteDataSource,
            moviesLocalDataSource
        )
    }

}














