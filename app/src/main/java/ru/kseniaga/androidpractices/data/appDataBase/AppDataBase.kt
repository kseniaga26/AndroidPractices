package ru.kseniaga.androidpractices.data.appDataBase


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.kseniaga.androidpractices.dao.TitleDao
import ru.kseniaga.androidpractices.model.TitleEntity


@Database(entities = [TitleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): TitleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movie_database1"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}