package com.exercise.movieapp.data.repository

import com.exercise.movieapp.data.model.APIResponse

import com.exercise.movieapp.data.util.Resource
import retrofit2.Response

class MoviesRepositoryImpl() {

    private fun responseToResource(response:Response<APIResponse>):Resource<APIResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
    

}