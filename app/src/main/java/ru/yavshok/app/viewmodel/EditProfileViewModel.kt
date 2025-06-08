package ru.yavshok.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yavshok.app.data.repository.UserRepository
import ru.yavshok.app.data.storage.TokenStorage

data class EditProfileUiState(
    val name: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isUpdateSuccessful: Boolean = false
)

class EditProfileViewModel(
    private val userRepository: UserRepository,
    private val tokenStorage: TokenStorage
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(EditProfileUiState())
    val uiState: StateFlow<EditProfileUiState> = _uiState.asStateFlow()
    
    init {
        // Load current user name on initialization
        loadCurrentUserName()
    }
    
    fun loadCurrentUserName() {
        // First: Load immediately from TokenStorage (no delay)
        val storedName = tokenStorage.getUserName()
        if (storedName != null) {
            Log.d("EditProfileViewModel", "⚡ Setting name immediately from TokenStorage: $storedName")
            _uiState.value = _uiState.value.copy(name = storedName)
        }
        
        // Then: Fetch fresh data from API to ensure accuracy
        viewModelScope.launch {
            try {
                Log.d("EditProfileViewModel", "🔄 Refreshing user name from API...")
                val response = userRepository.getCurrentUser()
                Log.d("EditProfileViewModel", "📡 API Response code: ${response.code()}")
                
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    Log.d("EditProfileViewModel", "✅ Response body: $userResponse")
                    userResponse?.user?.let { user ->
                        // Only update if different from stored value
                        if (user.name != storedName) {
                            Log.d("EditProfileViewModel", "🔄 Updating name from API: ${user.name}")
                            _uiState.value = _uiState.value.copy(name = user.name)
                        } else {
                            Log.d("EditProfileViewModel", "✅ API confirms stored name is current")
                        }
                    }
                } else {
                    Log.e("EditProfileViewModel", "❌ Failed to refresh user: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("EditProfileViewModel", "💥 Exception refreshing user: ${e.message}", e)
            }
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
                val response = userRepository.updateUserName(currentState.name)
                Log.d("EditProfileViewModel", "📡 Update API Response code: ${response.code()}")
                
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    Log.d("EditProfileViewModel", "✅ Update response body: $userResponse")
                    if (userResponse != null) {
                        // Update stored user name
                        val user = userResponse.user
                        Log.d("EditProfileViewModel", "💾 Saving updated user: id=${user.id}, email=${user.email}, name=${user.name}")
                        tokenStorage.saveUser(user.id, user.email, user.name)
                        
                        _uiState.value = currentState.copy(
                            isLoading = false,
                            isUpdateSuccessful = true
                        )
                        Log.d("EditProfileViewModel", "✅ Profile update successful!")
                    } else {
                        Log.e("EditProfileViewModel", "❌ Response body is null")
                        _uiState.value = currentState.copy(
                            isLoading = false,
                            errorMessage = "Server error"
                        )
                    }
                } else {
                    Log.e("EditProfileViewModel", "❌ Update failed: ${response.code()} - ${response.message()}")
                    val errorMessage = when (response.code()) {
                        401 -> "Unauthorized - please login again"
                        422 -> "Invalid name format"
                        else -> "Update failed: ${response.code()}"
                    }
                    _uiState.value = currentState.copy(
                        isLoading = false,
                        errorMessage = errorMessage
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