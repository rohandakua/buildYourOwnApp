package com.rohandakua.rapidopartnerhelperapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.rohandakua.rapidopartnerhelperapp.domain.usecase.SettingsUseCase
import androidx.lifecycle.viewModelScope
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.RapidoPartner
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SettingScreenViewModel(
    private val settingsUseCase: SettingsUseCase
) : ViewModel() {

    val darkMode: StateFlow<Boolean> = settingsUseCase.getDarkMode()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    val user: StateFlow<RapidoPartner?> = settingsUseCase.getUser()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun saveDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            settingsUseCase.saveDarkMode(enabled)
        }
    }

    fun saveUser(user: RapidoPartner) {
        viewModelScope.launch {
            settingsUseCase.saveUser(user)
        }
    }


    fun logoutUser() {
        viewModelScope.launch {
            settingsUseCase.logoutUser()
        }
    }
}
