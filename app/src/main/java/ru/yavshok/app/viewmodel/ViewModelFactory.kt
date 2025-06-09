package ru.yavshok.app.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.yavshok.app.data.network.NetworkModule
import ru.yavshok.app.data.repository.AuthRepository
import ru.yavshok.app.data.repository.UserRepository
import ru.yavshok.app.data.repository.ExperimentRepository
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.data.store.UserStore

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    
    private val apiService = NetworkModule.provideApiService()
    private val userApiService = NetworkModule.provideUserApiService(context)
    private val authRepository = AuthRepository(apiService)
    private val userRepository = UserRepository(userApiService)
    private val experimentRepository = ExperimentRepository(apiService, context)
    private val tokenStorage = TokenStorage(context)
    private val userStore = UserStore(authRepository, tokenStorage)
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginViewModel::class.java -> LoginViewModel(authRepository, tokenStorage, userStore) as T
            RegisterViewModel::class.java -> RegisterViewModel(authRepository, tokenStorage, userStore) as T
            ProfileViewModel::class.java -> ProfileViewModel(tokenStorage, authRepository, experimentRepository, userStore) as T
            EditProfileViewModel::class.java -> EditProfileViewModel(userRepository, authRepository, tokenStorage, userStore) as T
            MainViewModel::class.java -> MainViewModel() as T
            SplashViewModel::class.java -> SplashViewModel(experimentRepository, tokenStorage) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
} 