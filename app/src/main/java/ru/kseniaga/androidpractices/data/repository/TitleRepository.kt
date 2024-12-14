package ru.kseniaga.androidpractices.data.repository

import ru.kseniaga.androidpractices.data.api.ListTitleApi
import ru.kseniaga.androidpractices.data.mapper.TitleResponseToEntityMapper
import ru.kseniaga.androidpractices.domain.ITitleRepository
import ru.kseniaga.androidpractices.model.Title
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TitleRepository(private val api: ListTitleApi,
                      private val mapper: TitleResponseToEntityMapper
) : ITitleRepository
        {
        override suspend fun getTitle(type: String, status: String, page: Int, pageSize: Int, genres:String,): List<Title> {
            return withContext(Dispatchers.IO) {
                mapper.mapTitle(api.getTitles(
                    "2G9DDH6-K3E4V5B-KRDDKF0-NN150EF",10, 5,
                    selectFields = listOf("id", "name", "description", "year", "rating",
                        "movieLength", "genres", "countries", "poster", "persons"),
                    notNullFields= listOf("id", "name", "description", "year", "movieLength", "poster.url", "persons.name"),
                    sortField = listOf("rating.kp"),
                    sortType="1",
                    type= listOf(type),
                    status= listOf(status),
                    genre = listOf(genres),
                ))
            }
        }

        }
