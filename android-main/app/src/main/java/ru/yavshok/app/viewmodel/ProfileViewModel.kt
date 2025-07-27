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
import ru.yavshok.app.data.repository.ExperimentRepository
import ru.yavshok.app.data.store.UserStore
import ru.yavshok.app.data.model.AgeExperiment
import ru.yavshok.app.data.model.User
import ru.yavshok.app.data.model.determineAgeGroup
import ru.yavshok.app.data.model.getAgeGroupSubtitle

data class ProfileUiState(
    val profile: Profile? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoggedOut: Boolean = false
)

class ProfileViewModel(
    private val tokenStorage: TokenStorage,
    private val authRepository: AuthRepository,
    private val experimentRepository: ExperimentRepository,
    private val userStore: UserStore
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()
    
    init {
        loadProfile()
    }
    
    private fun loadProfile() {
        // 1. Get user data immediately from store (no loading state)
        val cachedUser = userStore.getUserImmediate()
        if (cachedUser != null) {
            // Show cached data instantly
            viewModelScope.launch {
                val ageExperiment = experimentRepository.getAgeExperiment()
                val subtitle = determineSubtitle(cachedUser, ageExperiment)
                
                val profile = Profile(
                    id = cachedUser.id,
                    name = cachedUser.name,
                    email = cachedUser.email,
                    subtitle = subtitle,
                    postsCount = 42,
                    followersCount = 567,
                    likesCount = 890,
                    photos = listOf("1", "2", "3", "4")
                )
                
                _uiState.value = _uiState.value.copy(
                    profile = profile,
                    isLoading = false,
                    errorMessage = null
                )
            }
        } else {
            // No cached data - load from API
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            viewModelScope.launch {
                val result = userStore.loadUser()
                if (result.isSuccess) {
                    loadProfile() // Recursive call to use cached data
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.exceptionOrNull()?.message ?: "Failed to load user"
                    )
                }
            }
        }
        
        // 2. Subscribe to user store changes for automatic updates
        viewModelScope.launch {
            userStore.userState.collect { userState ->
                if (userState.user != null) {
                    val ageExperiment = experimentRepository.getAgeExperiment()
                    val subtitle = determineSubtitle(userState.user, ageExperiment)
                    
                    val profile = Profile(
                        id = userState.user.id,
                        name = userState.user.name,
                        email = userState.user.email,
                        subtitle = subtitle,
                        postsCount = 42,
                        followersCount = 567,
                        likesCount = 890,
                        photos = listOf("1", "2", "3", "4")
                    )
                    
                    _uiState.value = _uiState.value.copy(
                        profile = profile,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            }
        }
    }
    
    fun logout() {
        viewModelScope.launch {
            tokenStorage.logout()
            userStore.clearUser()
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
    
    private fun determineSubtitle(user: User, ageExperiment: AgeExperiment?): String {
        return if (ageExperiment?.enabled == true && user.age != null) {
            val ageGroup = determineAgeGroup(user.age, ageExperiment)
            getAgeGroupSubtitle(ageGroup, ageExperiment)
        } else {
            "Котик в ШОКе"
        }
    }
} 