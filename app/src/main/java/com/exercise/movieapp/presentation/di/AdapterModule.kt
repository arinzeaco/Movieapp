package com.exercise.movieapp.presentation.di

import com.exercise.movieapp.presentation.adapter.MoviesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
   @Singleton
   @Provides
   fun provideMoviesAdapter():MoviesAdapter{
       return MoviesAdapter()
   }
}