package com.example.hap6_binar.presentation.ui.profile

import androidx.lifecycle.*
import com.example.hap6_binar.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val userName: LiveData<String?> = userRepository.getUserName().asLiveData()
    val userImage: LiveData<String?> = userRepository.getUserImage().asLiveData()

    fun saveUserName(name: String) {
        viewModelScope.launch {
            userRepository.setUserName(name)
        }
    }

    fun saveUserImage(imagePath: String) {
        viewModelScope.launch {
            userRepository.setUserImage(imagePath)
        }
    }
}
