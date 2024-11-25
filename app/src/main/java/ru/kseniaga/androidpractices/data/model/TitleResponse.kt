package ru.kseniaga.androidpractices.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class TitleResponse(
    val id: Long?,
    val name: String?,
    val poster: ImageResponse?,
    val description: String?,
    val year: Int?,
    val rating: RatingResponse?,
    val movieLength: Int?,
    val genres: List<GenreResponse?>?,
    val countries: List<CountryResponse?>?,
    @SerializedName("persons") val persons: List<PersonResponse?>?
)