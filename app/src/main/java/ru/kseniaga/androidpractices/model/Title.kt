package ru.kseniaga.androidpractices.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Title(
    val id: Long,
    val name: String,
    val description: String,
    val posterUrl: String,
    val year: Int,
    val type: String,
    val countries: List<String>,
    val genre: List<String>,
    val director: List<String>,
    val starring: List<String>,
    val movieLength: Int,
    val rating: Number,
    ) : Parcelable
