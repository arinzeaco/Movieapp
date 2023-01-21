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
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,


    @SerializedName("_id")
    val idd : String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("posterURL")
    val poster: String?,

    @SerializedName("ratings")
    val ratings: String?,

    @SerializedName("description")
    val description: String?,




):Serializable