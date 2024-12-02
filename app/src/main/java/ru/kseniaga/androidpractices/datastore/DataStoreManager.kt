package ru.kseniaga.androidpractices.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ru.kseniaga.androidpractices.utils.LocalUtils.isFilter

import kotlinx.coroutines.flow.map


private val  Context.dataStore: DataStore<Preferences> by preferencesDataStore("dataStore_settings")
class DataStoreManager(val context: Context) {

    suspend fun saveSettings(settingsData: SettingsData){
        context.dataStore.edit { pref->
            pref[stringPreferencesKey("type")] = settingsData.type
            pref[stringPreferencesKey("status")] = settingsData.status
            pref[stringPreferencesKey("genre")] = settingsData.genres
        }
        isFilter.value = true


    }
    fun getSettings() = context.dataStore.data.map { pref->
        return@map SettingsData(
            pref[stringPreferencesKey("type")] ?: "",
            pref[stringPreferencesKey("status")] ?: "",
            pref[stringPreferencesKey("genres")] ?: "",
        )
    }

    suspend fun resetSettings(){
        context.dataStore.edit { pref->
            pref[stringPreferencesKey("type")] = ""
            pref[stringPreferencesKey("status")] = ""
        }
        isFilter.value = false
    }
}