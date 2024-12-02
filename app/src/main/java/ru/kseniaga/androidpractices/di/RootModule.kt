package ru.kseniaga.androidpractices.di

import ru.kseniaga.androidpractices.data.mapper.TitleResponseToEntityMapper
import ru.kseniaga.androidpractices.data.repository.TitleRepository
import ru.kseniaga.androidpractices.domain.ITitleRepository
import ru.kseniaga.androidpractices.components.TitleViewModel
import ru.kseniaga.androidpractices.presentation.mapper.TitleUiMapper
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val rootModule = module {
    single<ITitleRepository> { TitleRepository(get(), get()) }
    factory { TitleResponseToEntityMapper() }
    factory { TitleUiMapper() }
    viewModel { TitleViewModel(get(), get(), get()) }
}