package ru.kseniaga.androidpractices.data.model

import com.google.gson.annotations.SerializedName

data class RatingResponse(
    @SerializedName("kp") val kp: Number?,
    @SerializedName("imdb") val imdb: Number?,
)
