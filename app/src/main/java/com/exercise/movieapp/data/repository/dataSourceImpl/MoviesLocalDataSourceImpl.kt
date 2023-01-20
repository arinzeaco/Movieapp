package com.exercise.movieapp.data.repository.dataSourceImpl

import com.exercise.movieapp.data.db.MoviesDAO
import com.exercise.movieapp.data.db.MoviesHideDAO
import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesHide
import com.exercise.movieapp.data.repository.dataSource.MoviesLocalDataSource
import kotlinx.coroutines.flow.Flow

class MoviesLocalDataSourceImpl(
    private val moviesDAO: MoviesDAO,
    private val moviesHideDAO: MoviesHideDAO
) : MoviesLocalDataSource {

    override suspend fun saveMoviesToDB(movies: Movies) {
        moviesDAO.insert(movies)
    }
    override suspend fun saveMoviesToDB(movies: List<Movies>) {
        moviesDAO.insert(movies)
    }

    override fun getSavedMovies(): Flow<List<Movies>> {
        return moviesDAO.getAllMovies()
    }

    override suspend fun deleteMoviesFromDB(movies: Movies) {
        moviesDAO.deleteMovies(movies)
    }

    override suspend fun saveMoviesHideToDB(moviesHide: MoviesHide) {
        moviesHideDAO.insert(moviesHide)
    }

    override suspend fun deleteMoviesHideFromDB(moviesHide: MoviesHide) {
        moviesHideDAO.deleteMoviesHide(moviesHide)
    }

    override fun getSavedMoviesHide(): Flow<List<MoviesHide>> {
        return moviesHideDAO.getAllMoviesHide()
    }
}