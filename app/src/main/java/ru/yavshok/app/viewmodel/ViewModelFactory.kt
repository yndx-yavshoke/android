package ru.yavshok.app.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.yavshok.app.data.network.NetworkModule
import ru.yavshok.app.data.repository.AuthRepository
import ru.yavshok.app.data.repository.UserRepository
import ru.yavshok.app.data.storage.TokenStorage

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    
    private val apiService = NetworkModule.provideApiService()
    private val userApiService = NetworkModule.provideUserApiService(context)
    private val authRepository = AuthRepository(apiService)
    private val userRepository = UserRepository(userApiService)
    private val tokenStorage = TokenStorage(context)
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginViewModel::class.java -> LoginViewModel(authRepository, tokenStorage) as T
            RegisterViewModel::class.java -> RegisterViewModel(authRepository, tokenStorage) as T
            ProfileViewModel::class.java -> ProfileViewModel(tokenStorage, authRepository) as T
            EditProfileViewModel::class.java -> EditProfileViewModel(userRepository, tokenStorage) as T
            MainViewModel::class.java -> MainViewModel() as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
} 