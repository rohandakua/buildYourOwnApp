package com.rohandakua.rapidopartnerhelperapp.data.repositoryImplementation

import android.util.Log
import com.rohandakua.rapidopartnerhelperapp.data.offline.datastore.AppDataStore
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.RapidoPartner
import com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces.SettingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class SettingRepositoryImplementation(
    private val appDataStore: AppDataStore
):SettingRepository {
    val TAG = "SettingRepositoryImplementation"

    override  fun getDarkMode(): Flow<Boolean> {
        return appDataStore.getDarkMode()
    }

    override suspend fun saveUser(user: RapidoPartner) {
        return appDataStore.saveUser(user)
    }

    override  fun getUser(): Flow<RapidoPartner?> {
        return appDataStore.getUser()
    }

    override suspend fun logoutUser() {
        try {
            withContext(Dispatchers.IO){
                appDataStore.clearUser()
            }
        }catch (e : Exception){
            Log.e(TAG, "error uoccured in logoutUser: ${e.message}")
        }
    }

    override suspend fun saveDarkMode(darkMode: Boolean) {
        try {
            withContext(Dispatchers.IO){
                appDataStore.saveDarkMode(darkMode)
            }
        }catch (e : Exception){
            Log.e(TAG, "error in saving the dark mode: ${e.message}")
        }
    }

    override suspend fun updateUserEarnings(partnerId: Int, earnings: Double) {
        try {
            withContext(Dispatchers.IO) {
                val user = appDataStore.getUser().first()
                user?.let { currentUser ->
                    val updatedUser = currentUser.copy(earning = (currentUser.earning ?: 0.0) + earnings)
                    appDataStore.saveUser(updatedUser)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "error in updating user earnings: ${e.message}")
        }
    }
}