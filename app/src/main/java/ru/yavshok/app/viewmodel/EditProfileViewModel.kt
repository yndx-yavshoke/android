package ru.yavshok.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yavshok.app.data.repository.UserRepository
import ru.yavshok.app.data.repository.AuthRepository
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.data.store.UserStore

data class EditProfileUiState(
    val name: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isUpdateSuccessful: Boolean = false
)

class EditProfileViewModel(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val tokenStorage: TokenStorage,
    private val userStore: UserStore
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(EditProfileUiState())
    val uiState: StateFlow<EditProfileUiState> = _uiState.asStateFlow()
    
    init {
        // Load current user name on initialization
        loadCurrentUserName()
    }
    
    fun loadCurrentUserName() {
        // Get name immediately from UserStore - no loading needed!
        val user = userStore.getUserImmediate()
        if (user != null) {
            Log.d("EditProfileViewModel", "⚡ Setting name immediately from UserStore: ${user.name}")
            _uiState.value = _uiState.value.copy(name = user.name)
        } else {
            Log.e("EditProfileViewModel", "❌ No user data in store")
            _uiState.value = _uiState.value.copy(errorMessage = "No user data available")
        }
    }
    
    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(name = name, errorMessage = null)
    }
    
    fun updateProfile() {
        val currentState = _uiState.value
        
        if (currentState.name.isBlank()) {
            _uiState.value = currentState.copy(errorMessage = "Name cannot be empty")
            return
        }
        
        if (currentState.name.length > 50) {
            _uiState.value = currentState.copy(errorMessage = "Name must be 50 characters or less")
            return
        }
        
        _uiState.value = currentState.copy(isLoading = true, errorMessage = null)
        
        viewModelScope.launch {
            try {
                Log.d("EditProfileViewModel", "🔄 Updating user name to: ${currentState.name}")
                
                // 1. Update UserStore immediately for instant UI update
                val result = userStore.updateUserName(currentState.name)
                if (result.isSuccess) {
                    Log.d("EditProfileViewModel", "⚡ UserStore updated instantly")
                    
                    // 2. Call API to sync with server
                    val response = userRepository.updateUserName(currentState.name)
                    Log.d("EditProfileViewModel", "📡 Update API Response code: ${response.code()}")
                    
                    if (response.isSuccessful) {
                        _uiState.value = currentState.copy(
                            isLoading = false,
                            isUpdateSuccessful = true
                        )
                        Log.d("EditProfileViewModel", "✅ Profile update successful!")
                    } else {
                        Log.e("EditProfileViewModel", "❌ API update failed: ${response.code()}")
                        // API failed but local update already happened - still show success
                        _uiState.value = currentState.copy(
                            isLoading = false,
                            isUpdateSuccessful = true
                        )
                    }
                } else {
                    Log.e("EditProfileViewModel", "❌ UserStore update failed")
                    _uiState.value = currentState.copy(
                        isLoading = false,
                        errorMessage = "Failed to update user data"
                    )
                }
            } catch (e: Exception) {
                Log.e("EditProfileViewModel", "💥 Exception updating profile: ${e.message}", e)
                _uiState.value = currentState.copy(
                    isLoading = false,
                    errorMessage = "Network error: ${e.message}"
                )
            }
        }
    }
    
    fun resetState() {
        _uiState.value = EditProfileUiState()
    }
} 