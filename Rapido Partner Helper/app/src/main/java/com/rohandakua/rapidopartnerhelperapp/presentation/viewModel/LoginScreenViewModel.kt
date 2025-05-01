package com.rohandakua.rapidopartnerhelperapp.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.RapidoPartner
import com.rohandakua.rapidopartnerhelperapp.domain.model.RapidoPartnerUiModel
import com.rohandakua.rapidopartnerhelperapp.domain.usecase.AuthUseCase
import com.rohandakua.rapidopartnerhelperapp.domain.usecase.SettingsUseCase
import kotlinx.coroutines.launch
class LoginScreenViewModel(
    private val authUseCase: AuthUseCase,
    private val settingsUseCase: SettingsUseCase
) : ViewModel() {
    // staates variables
    var loginText by mutableStateOf("")
    var passwordText by mutableStateOf("")

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    private val _userProfile = MutableLiveData<RapidoPartnerUiModel?>()
    val userProfile: LiveData<RapidoPartnerUiModel?> get() = _userProfile

    private val _darkMode = MutableLiveData<Boolean>()
    val darkMode: LiveData<Boolean> get() = _darkMode

    fun login() {
        val rapidoPartnerId = loginText.toIntOrNull()
        val password = passwordText

        if (rapidoPartnerId != null && password.isNotEmpty()) {
            viewModelScope.launch {
                val result = authUseCase.login(rapidoPartnerId, password)
                _loginResult.value = result
                if (result) {   // If login is successful, fetch the user profile
                    getUserProfile(rapidoPartnerId)
                }
            }
        } else {
            _loginResult.value = false
        }
    }
    fun getUserProfile(rapidoPartnerId: Int) {
        viewModelScope.launch {
            val profile = authUseCase.getUserProfile(rapidoPartnerId)
            _userProfile.value = profile?.let { RapidoPartnerUiModel.fromEntity(it) }
            _userProfile.value?.let { saveUser(it) }
        }
    }


    fun saveUser(user: RapidoPartnerUiModel) {
        viewModelScope.launch {
            settingsUseCase.saveUser(RapidoPartnerUiModel.toEntity(user))
        }
    }



}
