package ru.kseniaga.androidpractices.domain

import ru.kseniaga.androidpractices.model.Title

interface ITitleRepository {
        suspend fun getTitle(type: String, contentStatus: String, page: Int, pageSize: Int, genres:String,): List<Title>
}