package com.exercise.movieapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import com.exercise.movieapp.data.model.APIResponse
import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesFavorite
import com.exercise.movieapp.data.model.MoviesHide
import com.exercise.movieapp.data.util.Resource
import com.exercise.movieapp.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val app: Application,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getSearchedMoviesUseCase: GetSearchedMoviesUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase,
    private val saveMoviesHideUseCase: SaveMoviesHideUseCase,
    private val getSavedMoviesUseCase: GetSavedMoviesUseCase,
    private val getSavedMoviesHideUseCase: GetSavedMoviesHideUseCase,
    private val deleteSavedMoviesUseCase: DeleteSavedMoviesUseCase,
    private val deleteSavedMoviesHideUseCase: DeleteSavedMoviesHideUseCase
) : AndroidViewModel(app) {
    val movies: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    val progressBar: MutableLiveData<Boolean> = MutableLiveData(true)

    fun getMovies(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        if (isNetworkAvailable(app)) {
            val apiResult = getMoviesUseCase.execute(country, page)
            getMoviesResult(apiResult)
        }
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

    //search
    val searchedMovies: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun searchMovies(
        country: String,
        searchQuery: String,
        page: Int
    ) = viewModelScope.launch {
        searchedMovies.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val response = getSearchedMoviesUseCase.execute(
                    country,
                    searchQuery,
                    page
                )
                searchedMovies.postValue(response)
            } else {
                searchedMovies.postValue(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            searchedMovies.postValue(Resource.Error(e.message.toString()))
        }
    }

    //local data
    fun saveFavoriteMovies(moviesFavorite: MoviesFavorite) = viewModelScope.launch {
        saveMoviesUseCase.executesaveFavorite(moviesFavorite)
    }

    fun saveMovies(movies: List<Movies>) = viewModelScope.launch {
        saveMoviesUseCase.execute(movies)
    }


    fun getSavedMovies() = liveData {
        getSavedMoviesUseCase.execute().collect {
//            val filteredList:List<Movies>
//            if (getSavedMoviesHide().value?.isNotEmpty() == true) {
//                 filteredList = it.filterNot { element1 ->
//                    getSavedMoviesHide().value!!.any { element2 ->
//                        element1.id == element2.id
//                    }
//                }
                emit(it)

//            }
        }
    }

    fun getSavedFavoriteMovies() = liveData {
        getSavedMoviesUseCase.executeFavorite().collect {
            emit(it)
        }
    }


    fun deleteMovies(Movies: Movies) = viewModelScope.launch {
        deleteSavedMoviesUseCase.execute(Movies)
    }

    fun deleteMoviesFavorite(Movies: MoviesFavorite) = viewModelScope.launch {
        deleteSavedMoviesUseCase.executeFavorite(Movies)
    }

    fun saveMoviesHide(movieshide: MoviesHide) = viewModelScope.launch {
        saveMoviesHideUseCase.execute(movieshide)
    }

    fun getSavedMoviesHide() = liveData {
        getSavedMoviesHideUseCase.execute().collect {
            emit(it)
        }
    }

    fun deleteMoviesHide(moviesHide: MoviesHide) = viewModelScope.launch {
        deleteSavedMoviesHideUseCase.execute(moviesHide)
    }

    private fun getMoviesResult(result: Resource<APIResponse>) {
        when (result) {
            is Resource.Loading -> {
                progressBar.value = true
            }
            is Resource.Success -> {

                result.data?.let {
                    saveMovies(it.Movies)
                }
            }
            is Resource.Error -> {
                progressBar.value = false

            }
        }
    }

}














