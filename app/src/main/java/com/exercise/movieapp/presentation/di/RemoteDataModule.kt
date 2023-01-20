package com.exercise.movieapp.presentation.di

import com.exercise.movieapp.data.api.MoviesAPIService
import com.exercise.movieapp.data.repository.dataSource.MoviesRemoteDataSource
import com.exercise.movieapp.data.repository.dataSourceImpl.MoviesRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideMoviesRemoteDataSource(
        MoviesAPIService: MoviesAPIService
    ):MoviesRemoteDataSource{
       return MoviesRemoteDataSourceImpl(MoviesAPIService)
    }

}












