package ru.kseniaga.androidpractices.components

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import ru.kseniaga.androidpractices.domain.ITitleRepository
import ru.kseniaga.androidpractices.presentation.mapper.TitleUiMapper
import ru.kseniaga.androidpractices.presentation.model.TitleUiModel
import com.example.consecutivep.state.ListState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.UnknownHostException

class TitleViewModel(
    private val repository: ITitleRepository,
    private val uiMapper: TitleUiMapper,
) : ViewModel() {
    private val mutableState = MutableListState()
    val viewState = mutableState as ListState

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        mutableState.loading = false
        mutableState.error = when (exception) {
            is IOException -> "Problems with the Internet connection"
            is UnknownHostException -> "The server cannot be found"
            else -> "Error: ${exception.localizedMessage}"
        }
    }

    init {
        loadFilms()
    }

    fun loadFilms() {
        viewModelScope.launch(exceptionHandler) {
            mutableState.loading = true
            mutableState.error = null
            mutableState.items = emptyList()
            val titles = repository.getTitle(viewState.searchName)
            mutableState.items = titles.map{uiMapper.mapTitle(it)}
            mutableState.loading = false
        }
    }

    private class MutableListState : ListState {
        override var searchName: String by mutableStateOf("movie")
        override var items: List<TitleUiModel> by mutableStateOf(emptyList())
        override var error: String? by mutableStateOf(null)
        override var loading: Boolean by mutableStateOf(false)
    }
}
