package ru.kseniaga.androidpractices.utils

import ru.kseniaga.androidpractices.model.TitleEntity
import ru.kseniaga.androidpractices.presentation.model.TitleUiModel

object TitleEntityMapper {
        fun toEntity(movieUiModel: TitleUiModel): TitleEntity {
        return TitleEntity(
            id = movieUiModel.id.toString(),
            title = movieUiModel.name,
            genres = movieUiModel.genre,
            imageUrl = movieUiModel.posterUrl,
            year = movieUiModel.year.replace(" г.", "").toInt(),
            country = movieUiModel.countries,
            description = movieUiModel.description
        )
    }
}
