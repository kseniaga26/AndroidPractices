package ru.kseniaga.androidpractices.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import ru.kseniaga.androidpractices.data.serializer.ProfileSerializer
import ru.kseniaga.androidpractices.domain.model.ProfileEntity


class DataSourceProvider(val context: Context) {
    private val Context.profileDataStore: DataStore<ProfileEntity> by dataStore(
        fileName = "profile.pb",
        serializer = ProfileSerializer
    )
    fun provide() = context.profileDataStore
}