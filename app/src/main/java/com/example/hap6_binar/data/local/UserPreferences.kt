package com.example.hap6_binar.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

@Singleton
class UserPreferences @Inject constructor(private val context: Context) {

    companion object {
        val USER_NAME = stringPreferencesKey("USER_NAME")
        val USER_IMAGE = stringPreferencesKey("USER_IMAGE")
    }

    val userName: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_NAME]
    }

    val userImage: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_IMAGE]
    }

    suspend fun setUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME] = name
        }
    }

    suspend fun setUserImage(image: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_IMAGE] = image
        }
    }
}
