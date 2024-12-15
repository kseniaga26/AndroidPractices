package ru.kseniaga.androidpractices.di

import androidx.datastore.core.DataStore
import org.koin.android.ext.koin.androidContext
import ru.kseniaga.androidpractices.data.mapper.TitleResponseToEntityMapper
import ru.kseniaga.androidpractices.data.repository.TitleRepository
import ru.kseniaga.androidpractices.domain.ITitleRepository
import ru.kseniaga.androidpractices.components.TitleViewModel
import ru.kseniaga.androidpractices.presentation.mapper.TitleUiMapper
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.kseniaga.androidpractices.components.EditProfileViewModel
import ru.kseniaga.androidpractices.components.ProfileViewModel
import ru.kseniaga.androidpractices.data.repository.ProfileRepository
import ru.kseniaga.androidpractices.datastore.DataSourceProvider
import ru.kseniaga.androidpractices.datastore.DataStoreManager
import ru.kseniaga.androidpractices.domain.IProfileRepository
import ru.kseniaga.androidpractices.domain.model.ProfileEntity


val rootModule = module {
    single<ITitleRepository> { TitleRepository(get(), get()) }
    factory { TitleResponseToEntityMapper() }
    factory { TitleUiMapper() }
    single { ProfileRepository() }
    factory<DataStore<ProfileEntity>>(named("profile")) { DataSourceProvider(get()).provide() }
    single<IProfileRepository> { ProfileRepository() }
    single { DataStoreManager(get()) }
    viewModel { TitleViewModel(get(), get(), get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { EditProfileViewModel(get(), it.get(), androidContext()) }
}