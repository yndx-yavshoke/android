package ru.yavshok.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yavshok.app.data.model.Profile
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.data.repository.AuthRepository

data class ProfileUiState(
    val profile: Profile? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoggedOut: Boolean = false
)

class ProfileViewModel(
    private val tokenStorage: TokenStorage,
    private val authRepository: AuthRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()
    
    init {
        loadProfile()
    }
    
    private fun loadProfile() {
        // First: Show stored data immediately (no loading state)
        val userId = tokenStorage.getUserId()
        val userName = tokenStorage.getUserName() ?: "Neko"
        val userEmail = tokenStorage.getUserEmail() ?: ""
        
        val immediateProfile = Profile(
            id = userId,
            name = userName,
            email = userEmail,
            subtitle = "Ты молоденький котик",
            postsCount = 42,
            followersCount = 567,
            likesCount = 890,
            photos = listOf("1", "2", "3", "4")
        )
        
        _uiState.value = _uiState.value.copy(
            profile = immediateProfile,
            isLoading = false,
            errorMessage = null
        )
        Log.d("ProfileViewModel", "⚡ Profile loaded immediately from storage: $userName")
        
        // Then: Refresh from API in background
        viewModelScope.launch {
            try {
                val token = tokenStorage.getToken()
                if (token != null) {
                    // Fetch fresh user data from API
                    val response = authRepository.getCurrentUser(token)
                    if (response.isSuccessful) {
                        val userResponse = response.body()
                        if (userResponse != null) {
                            val user = userResponse.user
                            // Only update if data actually changed
                            if (user.name != userName || user.email != userEmail) {
                                Log.d("ProfileViewModel", "🔄 Updating profile from API: ${user.name}")
                                val updatedProfile = Profile(
                                    id = user.id,
                                    name = user.name,
                                    email = user.email,
                                    subtitle = "Ты молоденький котик",
                                    postsCount = 42,
                                    followersCount = 567,
                                    likesCount = 890,
                                    photos = listOf("1", "2", "3", "4")
                                )
                                
                                _uiState.value = _uiState.value.copy(
                                    profile = updatedProfile,
                                    errorMessage = null
                                )
                            } else {
                                Log.d("ProfileViewModel", "✅ API confirms stored profile is current")
                            }
                        } else {
                            _uiState.value = _uiState.value.copy(
                                errorMessage = "Ошибка получения данных пользователя"
                            )
                        }
                    } else {
                        _uiState.value = _uiState.value.copy(
                            errorMessage = "Ошибка авторизации: ${response.code()}"
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Ошибка загрузки профиля: ${e.message}"
                )
            }
        }
    }
    
    fun logout() {
        viewModelScope.launch {
            tokenStorage.logout()
            _uiState.value = _uiState.value.copy(isLoggedOut = true)
        }
    }
    
    fun resetLogoutState() {
        _uiState.value = _uiState.value.copy(isLoggedOut = false)
    }
    
    fun isLoggedIn(): Boolean {
        return tokenStorage.isLoggedIn()
    }
    
    fun refreshProfile() {
        Log.d("ProfileViewModel", "🔄 Refreshing profile...")
        loadProfile()
    }
} 