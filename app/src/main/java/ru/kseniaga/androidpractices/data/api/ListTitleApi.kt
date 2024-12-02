package ru.kseniaga.androidpractices.data.api

import ru.kseniaga.androidpractices.data.model.TitlePagingResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface ListTitleApi {
    @GET("movie")
    suspend fun getTitles(
        @Header("X-API-KEY") apiKey: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectFields: List<String>,
        @Query("notNullFields") notNullFields: List<String>,
        @Query("sortField") sortField: List<String>,
        @Query("type") type: List<String>,
        @Query("status") status: List<String>,
        @Query("genres") genre: List<String>,
        @Query("sortType") sortType: String,
    ): TitlePagingResponse

}