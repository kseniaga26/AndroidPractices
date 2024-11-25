package ru.kseniaga.androidpractices.domain

import ru.kseniaga.androidpractices.model.Title

interface ITitleRepository {
        suspend fun getTitle(nameSearch: String): List<Title>
}