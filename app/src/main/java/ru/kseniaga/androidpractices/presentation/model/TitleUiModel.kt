package ru.kseniaga.androidpractices.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class TitleUiModel(
    val id: Long,
    val name: String,
    val description: String,
    val posterUrl: String,
    val year: String,
    val type: String,
    val countries: String,
    val genre: String,
    val director: String,
    val starring: List<String>,
    val movieLength: String,
    val rating: Number,
): Parcelable