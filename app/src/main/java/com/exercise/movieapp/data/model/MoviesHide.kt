package com.exercise.movieapp.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "MoviesHide"
)
data class MoviesHide(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,

    @SerializedName("original_title")
    val title: String?,

    @SerializedName("poster_path")
    val poster: String?,

    @SerializedName("adult")
    val adult: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("favorite")
    var favorite: Int?=1,

):Serializable