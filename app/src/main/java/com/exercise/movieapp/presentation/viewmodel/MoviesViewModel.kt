package com.exercise.movieapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.exercise.movieapp.data.model.APIResponse
import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.data.model.MoviesFavorite
import com.exercise.movieapp.data.model.MoviesHide
import com.exercise.movieapp.data.util.Resource
import com.exercise.movieapp.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val app: Application,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase,
    private val saveMoviesHideUseCase: SaveMoviesHideUseCase,
    private val getSavedMoviesUseCase: GetSavedMoviesUseCase,
    private val getSavedMoviesHideUseCase: GetSavedMoviesHideUseCase,
    private val deleteSavedMoviesUseCase: DeleteSavedMoviesUseCase,
    private val deleteSavedMoviesHideUseCase: DeleteSavedMoviesHideUseCase
) : AndroidViewModel(app) {
    val movies: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar
    val moviesHide: MutableLiveData<List<MoviesHide>> = MutableLiveData()
    val moviesFilter: MutableLiveData<List<Movies>> = MutableLiveData()
    val job = Job()
    val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    fun getMovies() = viewModelScope.launch(Dispatchers.IO) {
        if (isNetworkAvailable(app)) {
            val apiResult = getMoviesUseCase.execute()
            getMoviesResult(apiResult)
        }else{
            searchedMovies.postValue(Resource.Error("No internet connection"))

        }
    }
    val searchedMovies: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

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

    //local data
    fun saveFavoriteMovies(moviesFavorite: MoviesFavorite) = viewModelScope.launch {
        saveMoviesUseCase.executesaveFavorite(moviesFavorite)
    }

    fun saveMovies(movies: List<Movies>) = viewModelScope.launch {
        saveMoviesUseCase.execute(movies)
    }

    fun getSavedMovies() = liveData {
        getSavedMoviesUseCase.execute().collect {


            emit(it)

//            }
        }
    }
    fun getSavedMovies(search:String, idd: List<String>) {
        coroutineScope.launch {

            getSavedMoviesUseCase.execute(search,idd).collect {
                moviesFilter.value = it
            }
        }

    }

    fun getSavedFavoriteMovies() = liveData {
        getSavedMoviesUseCase.executeFavorite().collect {

            emit(it)
        }
    }


    fun deleteMovies() = viewModelScope.launch {
        deleteSavedMoviesUseCase.execute()
    }

    fun deleteMoviesFavorite(Movies: MoviesFavorite) = viewModelScope.launch {
        deleteSavedMoviesUseCase.executeFavorite(Movies)
    }

    fun saveMoviesHide(movieshide: MoviesHide) = viewModelScope.launch {
        saveMoviesHideUseCase.execute(movieshide)
    }

    fun getSavedMoviesHide() {
//        getSavedMoviesHideUseCase.execute()
        coroutineScope.launch {

            getSavedMoviesHideUseCase.execute().collect {

                moviesHide.value = it
            }
        }
    }

    fun deleteMoviesHide(moviesHide: MoviesHide) = viewModelScope.launch {
        deleteSavedMoviesHideUseCase.execute(moviesHide)
    }

    private fun getMoviesResult(result: Resource<APIResponse>) {
        when (result) {
            is Resource.Loading -> {
                _progressBar.value = true
            }
            is Resource.Success -> {

                result.data?.let {
                    viewModelScope.launch {
                        _progressBar.value = false

                        deleteSavedMoviesUseCase.execute()
                    }

                    saveMovies(it.movies)
                }
            }
            is Resource.Error -> {
                _progressBar.value = false

            }
        }
    }


}














