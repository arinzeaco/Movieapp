package com.exercise.movieapp.data.model


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "Movies",
)
data class Movies(
    @PrimaryKey(autoGenerate = false)
    val id : Int? = null,

    @SerializedName("original_title")
    val title: String?,

    @SerializedName("poster_path")
    val poster: String?,

    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("overview")
    val description: String?,




):Serializable