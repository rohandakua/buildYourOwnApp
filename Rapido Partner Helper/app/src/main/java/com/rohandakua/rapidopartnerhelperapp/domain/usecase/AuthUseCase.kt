package com.rohandakua.rapidopartnerhelperapp.domain.usecase

import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.RapidoPartner
import com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces.AuthInterface
import kotlinx.coroutines.flow.Flow

class AuthUseCase(
    private val authRepository: AuthInterface
) {
    suspend fun login(rapidoPartnerId: Int, password: String): Boolean {
        return authRepository.login(rapidoPartnerId, password)
    }

    suspend fun getUserProfile(rapidoPartnerId: Int): RapidoPartner? {
        return authRepository.getUserProfile(rapidoPartnerId)
    }
} 