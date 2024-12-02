package ru.kseniaga.androidpractices.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.kseniaga.androidpractices.model.TitleEntity


@Dao
interface TitleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: TitleEntity)

    @Delete
    suspend fun delete(movie: TitleEntity)

    @Query("SELECT * FROM favorite_movies")
    suspend fun getAllFavoriteMovies(): List<TitleEntity>

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId LIMIT 1")
    suspend fun getMovieById(movieId: String): TitleEntity?
}