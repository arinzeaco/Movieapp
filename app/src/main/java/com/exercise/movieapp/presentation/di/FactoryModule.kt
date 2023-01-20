//package com.exercise.movieapp.presentation.di
//
//import android.app.Application
//import com.exercise.movieapp.domain.usecase.*
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//class FactoryModule {
//    @Singleton
//    @Provides
//  fun provideMoviesViewModelFactory(
//     application: Application,
//     getMoviesUseCase: GetMoviesUseCase,
//     getSearchedMoviesUseCase: GetSearchedMoviesUseCase,
//     saveMoviesUseCase: HideOrLikeMovieUseCase,
//     getSavedMoviesUseCase: GetSavedMoviesUseCase,
//     deleteSavedMoviesUseCase: DeleteSavedMoviesUseCase
//  ):MoviesViewModelFactory{
//      return MoviesViewModelFactory(
//          application,
//          getMoviesUseCase,
//          getSearchedMoviesUseCase,
//          saveMoviesUseCase,
//          getSavedMoviesUseCase,
//          deleteSavedMoviesUseCase
//      )
//  }
//
//}
//
//
//
//
//
//
//
//
