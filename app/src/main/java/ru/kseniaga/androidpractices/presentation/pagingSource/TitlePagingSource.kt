package ru.kseniaga.androidpractices.presentation.pagingSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.kseniaga.androidpractices.domain.ITitleRepository
import ru.kseniaga.androidpractices.presentation.mapper.TitleUiMapper
import ru.kseniaga.androidpractices.presentation.model.TitleUiModel
import java.io.IOException
import java.net.SocketTimeoutException

class TitlePagingSource(
    private val repository: ITitleRepository,
    private val uiMapper: TitleUiMapper,
    private val type: String,
    private val contentStatus: String,
    private val genres: String,
) : PagingSource<Int, TitleUiModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TitleUiModel> {
        val currentPage = params.key ?: 1
        Log.d("TitlePagingSource", "Loading page: $currentPage with page size: ${params.loadSize}")
        return try {
            val movies = repository.getTitle(
                type = type,
                contentStatus = contentStatus,
                page = currentPage,
                pageSize = params.loadSize,
                genres = genres
            )
            Log.d("TitlePagingSource", "Loaded ${movies.size} items")

            LoadResult.Page(
                data = movies.map { uiMapper.mapTitle(it) },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.isEmpty()) null else currentPage + 1
            )


        } catch (e: SocketTimeoutException) {
            LoadResult.Error(IOException("Server took too long to respond. Please try again."))
        }
        catch (e: Exception) {
            Log.e("TitlePagingSource", "Error loading data", e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TitleUiModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
