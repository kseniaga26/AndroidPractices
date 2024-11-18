package ru.kseniaga.androidpractices.presentation.mapper

import ru.kseniaga.androidpractices.presentation.model.TitleUiModel
import ru.kseniaga.androidpractices.model.Title

class TitleUiMapper {

    fun mapTitle(entity: Title): TitleUiModel {
        return TitleUiModel(
            entity.id,
            entity.name,
            entity.description,
            entity.posterUrl,
            "${entity.year}",
            entity.type,
            entity.countries.joinToString(),
            entity.genre.joinToString(),
            entity.director.joinToString(),
            entity.starring,
            "${entity.movieLength} мин.",
            entity.rating,
        )
    }
}