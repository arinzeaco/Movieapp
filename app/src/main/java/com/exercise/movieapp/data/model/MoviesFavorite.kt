package com.exercise.movieapp.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "MoviesFavorite"
)
data class MoviesFavorite(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,

    @SerializedName("_id")
    val idd : String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("poster_path")
    val poster: String?,

    @SerializedName("ratings")
    val ratings: String?,

    @SerializedName("description")
    val description: String?,



):Serializable