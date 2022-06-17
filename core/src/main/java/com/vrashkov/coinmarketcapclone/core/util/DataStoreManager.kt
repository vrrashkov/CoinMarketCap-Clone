package com.vrashkov.coinmarketcapclone.core.util

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
private val Context.settingsDataStore by preferencesDataStore("settings")

class DataStoreManager @Inject constructor(@ApplicationContext appContext: Context) {

    private val settingsDataStore = appContext.settingsDataStore

    companion object {
        val authToken = stringPreferencesKey("authToken")
    }

    suspend fun setAuthToken(value: String) {
        settingsDataStore.edit { settings ->
            settings[authToken] = value
        }
    }

    val getAuthToken: Flow<String> = appContext.settingsDataStore.data.map { settings ->
        settings[authToken] ?: ""
    }
}