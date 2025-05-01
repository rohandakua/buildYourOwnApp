package com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces

import androidx.datastore.preferences.core.edit
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.RapidoPartner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SettingRepository {
    suspend fun getDarkMode(): Flow<Boolean>
    suspend fun saveUser(user: RapidoPartner)
    suspend fun getUser (): Flow<RapidoPartner?>
    suspend fun logoutUser()
    suspend fun saveDarkMode(darkMode: Boolean)

}