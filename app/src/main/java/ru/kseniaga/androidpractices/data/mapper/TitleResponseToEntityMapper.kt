package ru.kseniaga.androidpractices.data.mapper

import ru.kseniaga.androidpractices.data.model.TitlePagingResponse
import ru.kseniaga.androidpractices.model.Title

class TitleResponseToEntityMapper {

        fun mapTitle(response: TitlePagingResponse): List<Title> {
            return response.docs?.map {
                Title(
                    id = it?.id ?: 0L,
                    name = it?.name.orEmpty(),
                    posterUrl = it?.poster?.previewUrl.orEmpty(),
                    type = it?.name.orEmpty(),
                    year = it?.year ?: 0,
                    description = it?.description.orEmpty(),
                    countries = it?.countries.orEmpty().map {it?.name.toString() },
                    genre = it?.genres.orEmpty().map {it?.name.toString() },
                    director = it?.persons.orEmpty().filter { it?.profession == "режиссеры" }.map { it?.name.orEmpty()},
                    starring = it?.persons.orEmpty().filter { it?.profession == "актеры"}.map { it?.name.orEmpty()},
                    rating = it?.rating?.kp ?: 0.0,
                    movieLength = it?.movieLength ?: 0,
                )
            }.orEmpty()
        }
    }