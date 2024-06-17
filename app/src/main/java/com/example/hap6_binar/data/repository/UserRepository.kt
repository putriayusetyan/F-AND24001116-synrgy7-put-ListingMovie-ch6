package com.example.hap6_binar.data.repository

import com.example.hap6_binar.data.local.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userPreferences: UserPreferences) {
    fun getUserName(): Flow<String?> {
        return userPreferences.userName
    }

    suspend fun setUserName(name: String) {
        userPreferences.setUserName(name)
    }

    fun getUserImage(): Flow<String?> {
        return userPreferences.userImage
    }

    suspend fun setUserImage(image: String) {
        userPreferences.setUserImage(image)
    }
}
