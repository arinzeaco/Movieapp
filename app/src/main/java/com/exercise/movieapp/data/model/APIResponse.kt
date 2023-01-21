package com.exercise.movieapp.data.model


import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("data")
    val movies: List<Movies>,

    @SerializedName("page")
    val page: String,
)