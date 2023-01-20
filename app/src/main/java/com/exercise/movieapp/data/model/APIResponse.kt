package com.exercise.movieapp.data.model


import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("results")
    val Movies: List<Movies>,

    @SerializedName("page")
    val page: String,
)