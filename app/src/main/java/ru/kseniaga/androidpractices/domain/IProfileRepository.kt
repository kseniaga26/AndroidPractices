package ru.kseniaga.androidpractices.domain

import kotlinx.coroutines.flow.Flow
import ru.kseniaga.androidpractices.domain.model.ProfileEntity

interface IProfileRepository {
    suspend fun getProfile(): ProfileEntity?
    suspend fun setProfile(photoUri: String, name: String, url: String): ProfileEntity
    suspend fun observeProfile(): Flow<ProfileEntity>
}