package com.rohandakua.rapidopartnerhelperapp.domain.usecase

import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.RapidoPartner
import com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces.SettingRepository
import kotlinx.coroutines.flow.Flow

class SettingsUseCase(
    private val settingsRepository: SettingRepository
) {
    fun getDarkMode(): Flow<Boolean> {
        return settingsRepository.getDarkMode()
    }

    suspend fun saveUser(user: RapidoPartner) {
        settingsRepository.saveUser(user)
    }

    fun getUser(): Flow<RapidoPartner?> {
        return settingsRepository.getUser()
    }

    suspend fun logoutUser() {
        settingsRepository.logoutUser()
    }

    suspend fun saveDarkMode(darkMode: Boolean) {
        settingsRepository.saveDarkMode(darkMode)
    }
} 