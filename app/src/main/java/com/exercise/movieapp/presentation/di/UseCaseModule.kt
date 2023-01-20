package com.exercise.movieapp.presentation.di

import com.exercise.movieapp.domain.repository.MoviesRepository
import com.exercise.movieapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
   @Singleton
   @Provides
   fun provideGetMoviesUseCase(
       MoviesRepository: MoviesRepository
   ):GetMoviesUseCase{
      return GetMoviesUseCase(MoviesRepository)
   }

   @Singleton
   @Provides
   fun provideGetSearchedMoviesUseCase(
      MoviesRepository: MoviesRepository
   ):GetSearchedMoviesUseCase{
      return GetSearchedMoviesUseCase(MoviesRepository)
   }

   @Singleton
   @Provides
   fun provideSaveMoviesUseCase(
      MoviesRepository: MoviesRepository
   ):SaveMoviesUseCase{
      return SaveMoviesUseCase(MoviesRepository)
   }

   @Singleton
   @Provides
   fun provideGetSavedMoviesUseCase(
      MoviesRepository: MoviesRepository
   ):GetSavedMoviesUseCase{
      return GetSavedMoviesUseCase(MoviesRepository)
   }

   @Singleton
   @Provides
   fun provideDeleteSavedMoviesUseCase(
      MoviesRepository: MoviesRepository
   ):DeleteSavedMoviesUseCase{
      return DeleteSavedMoviesUseCase(MoviesRepository)
   }


   @Singleton
   @Provides
   fun provideSaveMoviesHideUseCase(
      MoviesRepository: MoviesRepository
   ):SaveMoviesHideUseCase{
      return SaveMoviesHideUseCase(MoviesRepository)
   }

   @Singleton
   @Provides
   fun provideGetSavedMoviesHideUseCase(
      MoviesRepository: MoviesRepository
   ):GetSavedMoviesHideUseCase{
      return GetSavedMoviesHideUseCase(MoviesRepository)
   }

   @Singleton
   @Provides
   fun provideDeleteSavedMoviesHideUseCase(
      MoviesRepository: MoviesRepository
   ):DeleteSavedMoviesHideUseCase{
      return DeleteSavedMoviesHideUseCase(MoviesRepository)
   }
}


















