package ru.kseniaga.androidpractices.data.model
import androidx.annotation.Keep

@Keep
data class TitlePagingResponse(
    val docs: List<TitleResponse?>?,
    val total: Int?,
    val limit: Int?,
    val page: Int?,
    val pages: Int?
)