package com.exercise.movieapp.data.repository

import com.exercise.movieapp.data.model.APIResponse
import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesFavorite
import com.exercise.movieapp.data.model.MoviesHide
import com.exercise.movieapp.data.repository.dataSource.MoviesLocalDataSource
import com.exercise.movieapp.data.repository.dataSource.MoviesRemoteDataSource
import com.exercise.movieapp.data.util.Resource
import com.exercise.movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MoviesRepositoryImpl(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
):MoviesRepository {
    override suspend fun getMovies(): Resource<APIResponse> {
        return responseToResource(moviesRemoteDataSource.getMovies())
    }

    override suspend fun getSearchedMovies(
        searchQuery: String,
    ): Resource<APIResponse> {
        return responseToResource(
            moviesRemoteDataSource.getSearchedMovies(searchQuery)
        )
    }

    private fun responseToResource(response:Response<APIResponse>):Resource<APIResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
    

    override suspend fun saveMoviesToDB(movies: List<Movies>) {
        moviesLocalDataSource.saveMoviesToDB(movies)
    }

    override suspend fun saveFavoriteMoviesToDB(moviesFavorite: MoviesFavorite) {
        moviesLocalDataSource.saveFavoriteMoviesToDB(moviesFavorite)
    }

    override suspend fun saveMoviesToDB(movies: Movies) {
        moviesLocalDataSource.saveMoviesToDB(movies)
    }

    override suspend fun deleteMovies(movies: Movies) {
        moviesLocalDataSource.deleteMoviesFromDB(movies)
    }

    override suspend fun deleteMoviesFavorite(moviesFavorite: MoviesFavorite) {
        moviesLocalDataSource.deleteMoviesFavoriteFromDB(moviesFavorite)
    }



    override fun getSavedMovies(ids: List<Int>): Flow<List<Movies>> {
        return moviesLocalDataSource.getSavedMovies(ids)
    }

    override fun getSavedMovies(): Flow<List<Movies>> {
        return moviesLocalDataSource.getSavedMovies()
    }

    override fun getSavedMoviesFavorite(): Flow<List<MoviesFavorite>> {
        return moviesLocalDataSource.getSavedMoviesFavorite()
    }


    override suspend fun saveMoviesHide(moviesHide: MoviesHide) {
        moviesLocalDataSource.saveMoviesHideToDB(moviesHide)
    }

    override suspend fun deleteMoviesHide(moviesHide: MoviesHide) {
        return moviesLocalDataSource.deleteMoviesHideFromDB(moviesHide)
    }

    override fun getSavedMoviesHide(): Flow<List<MoviesHide>> {
        return moviesLocalDataSource.getSavedMoviesHide()
    }
}