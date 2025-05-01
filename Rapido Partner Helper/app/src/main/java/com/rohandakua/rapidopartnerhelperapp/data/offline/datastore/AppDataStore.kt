package com.rohandakua.rapidopartnerhelperapp.data.offline.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import androidx.datastore.preferences.core.*
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.RapidoPartner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * @AppDataStore
 * this class is used to store data in the app's data store
 * it will store the single tuple of the RapidoPartner that is the logged in user and also the setting for Dark Mode in the setting page
 */
class AppDataStore (
    private val context: Context
) {
    private val Context.appDataStore : DataStore<Preferences> by preferencesDataStore(name = "app_data_store")
    private val gson = Gson()
    private val USER_KEY = stringPreferencesKey("user")
    private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
    suspend fun saveUser(user: RapidoPartner) {
        context.appDataStore.edit { preferences ->
            preferences[USER_KEY] = gson.toJson(user)
        }
    }
    suspend fun getUser (): Flow<RapidoPartner?> {
        return context.appDataStore.data.map {
            preferences ->
            preferences[USER_KEY]?.let{
                gson.fromJson(it, RapidoPartner::class.java)
            }
        }
    }
    suspend fun clearUser(){
        context.appDataStore.edit { preferences ->
            preferences.remove(USER_KEY)
        }
    }

    suspend fun saveDarkMode(darkMode: Boolean) {
        context.appDataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = darkMode
        }
    }
    suspend fun getDarkMode(): Flow<Boolean> {
        return context.appDataStore.data.map {
            preferences ->
            preferences[DARK_MODE_KEY] ?: false
        }
    }



}