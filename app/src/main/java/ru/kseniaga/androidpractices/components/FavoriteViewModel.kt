package ru.kseniaga.androidpractices.components

import android.app.Application
import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kseniaga.androidpractices.dao.TitleDao
import ru.kseniaga.androidpractices.data.appDataBase.AppDatabase
import ru.kseniaga.androidpractices.model.TitleEntity
import java.io.ByteArrayOutputStream

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val movieDao: TitleDao = AppDatabase.getDatabase(application).movieDao()
    private val _favoriteMovieList = mutableStateListOf<TitleEntity>()
    val favoriteMovieList: List<TitleEntity> = _favoriteMovieList
    private val _movieById = mutableStateOf<TitleEntity?>(null)

    fun loadFavoriteMovie() {
        viewModelScope.launch {
            _favoriteMovieList.clear()
            _favoriteMovieList.addAll(movieDao.getAllFavoriteMovies())
        }
    }

    fun addMovieToFavorite(movie: TitleEntity, imageUrl: String? = null) {
        viewModelScope.launch {

            if (!imageUrl.isNullOrEmpty()) {
                saveImageToDatabase(movie, imageUrl)
            } else {
                movieDao.insert(movie)
            }
            _favoriteMovieList.clear()
            _favoriteMovieList.addAll(movieDao.getAllFavoriteMovies())
        }
    }

    fun removeMovieFromFavorite(movie: TitleEntity) {
        viewModelScope.launch {
            movieDao.delete(movie)
            loadFavoriteMovie()
        }
    }

    private suspend fun saveImageToDatabase(movie: TitleEntity, imageUrl: String) {
        val context = getApplication<Application>().applicationContext

        val bitmap = withContext(Dispatchers.IO) {
            try {
                Glide.with(context)
                    .asBitmap()
                    .load(imageUrl)
                    .submit()
                    .get()
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }

        bitmap?.let { bmp ->

            val byteArrayOutputStream = ByteArrayOutputStream()
            bmp.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()


            val movieWithImage = movie.copy(imageBytes = byteArray)
            movieDao.insert(movieWithImage)
        }
    }
}
