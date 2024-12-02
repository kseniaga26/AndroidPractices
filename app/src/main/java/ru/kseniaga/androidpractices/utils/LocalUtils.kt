package ru.kseniaga.androidpractices.utils

import androidx.compose.runtime.mutableStateOf

object LocalUtils {
    var isFilter = mutableStateOf(false)

    val types = listOf("movie", "tv-series", "animated-series", "cartoon", "anime",)

    val contentStatus = listOf("announced", "completed", "filming", "post-production", "pre-production")

    val genres = listOf("комедия", "мелодрама", "триллер", "криминал", "боевик", "драма", "ужасы")

}