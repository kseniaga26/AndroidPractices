package ru.kseniaga.androidpractices.components

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ru.kseniaga.androidpractices.datastore.DataStoreManager
import ru.kseniaga.androidpractices.domain.ITitleRepository
import ru.kseniaga.androidpractices.presentation.mapper.TitleUiMapper
import ru.kseniaga.androidpractices.presentation.model.TitleUiModel
import com.example.consecutivep.state.ListState
import ru.kseniaga.androidpractices.utils.LocalUtils.isFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import ru.kseniaga.androidpractices.presentation.pagingSource.TitlePagingSource

class TitleViewModel(
    private val repository: ITitleRepository,
    private val uiMapper: TitleUiMapper,
    context: Context,
) : ViewModel() {
    private val mutableState = MutableListState()
    val viewState = mutableState as ListState

    private val dataStoreManager = DataStoreManager(context)

    private val _filterParams = MutableStateFlow(FilterParams())
    val filterParams = _filterParams
    val pagedMovies: Flow<PagingData<TitleUiModel>> =
        filterParams.flatMapLatest { params ->
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    prefetchDistance = 5,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    TitlePagingSource(
                        repository = repository,
                        uiMapper = uiMapper,
                        type = params.type,
                        contentStatus = params.contentStatus,
                        genres = params.contentGenres
                    )
                }
            ).flow.cachedIn(viewModelScope)
        }

    init {
        loadTmp()
    }
    fun loadTmp() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dataStoreManager.getSettings().collect { settings ->
                    val savedType = settings.type.split(",").filter { it.isNotBlank() }
                    val savedContentStatus = settings.status.split(",").filter { it.isNotBlank() }
                    val savedGenres = settings.genres.split(",").filter { it.isNotBlank() }

                    isFilter.value =
                        savedType.isNotEmpty() || savedContentStatus.isNotEmpty() || savedGenres.isNotEmpty()
                    if (isFilter.value) {
                        updateFilters(savedType.getOrNull(0) ?: "", savedContentStatus.getOrNull(0) ?: "", savedGenres.getOrNull(0) ?: "")
                    }
                }
            } catch (e: Exception) {
                Log.d("TitleViewModel", "${e.message}")
            }
        }
    }

    fun updateFilters(type: String, contentStatus: String, genres: String,) {
        _filterParams.value = FilterParams(type, contentStatus, genres)
    }

    companion object {
        private const val DEFAULT_CONTENT_STATUS = "completed"
        private const val DEFAULT_SEARCH_NAME = "movie"
        private const val DEFAULT_CONTENT_GENRE = "genres"
    }

    data class FilterParams(
        val type: String = DEFAULT_SEARCH_NAME,
        val contentStatus: String = DEFAULT_CONTENT_STATUS,
        val contentGenres: String = DEFAULT_CONTENT_GENRE
    )

    private class MutableListState : ListState {

        override var items: List<TitleUiModel> by mutableStateOf(emptyList())
        override var searchName: String by mutableStateOf(DEFAULT_SEARCH_NAME)
        override var filterContentStatus: String by mutableStateOf(DEFAULT_CONTENT_STATUS)
        override var filterGenres: String by mutableStateOf(DEFAULT_CONTENT_GENRE)
        override var error: String? by mutableStateOf(null)
        override var loading: Boolean by mutableStateOf(false)
    }
}
