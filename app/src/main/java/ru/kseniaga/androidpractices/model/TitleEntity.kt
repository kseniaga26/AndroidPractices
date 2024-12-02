package ru.kseniaga.androidpractices.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class TitleEntity(
    @PrimaryKey val id: String = "",
    val title: String = "",
    val genres: String = "",
    val imageUrl: String = "",
    val year: Int = 0,
    val country: String = "",
    val description: String = "",
    val imageBytes: ByteArray? = null
)