package ru.yavshok.app.data.store

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.yavshok.app.data.model.User
import ru.yavshok.app.data.repository.AuthRepository
import ru.yavshok.app.data.storage.TokenStorage

data class UserState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class UserStore(
    private val authRepository: AuthRepository,
    private val tokenStorage: TokenStorage
) {
    private val _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState.asStateFlow()
    
    // Immediate access to current user data
    val currentUser: User? get() = _userState.value.user
    val isLoggedIn: Boolean get() = tokenStorage.isLoggedIn() && currentUser != null
    
    /**
     * Load user data from API and cache it
     * Call this after login/register or when need to refresh
     */
    suspend fun loadUser(): Result<User> {
        val token = tokenStorage.getToken()
        if (token == null) {
            _userState.value = UserState(error = "No token")
            return Result.failure(Exception("No token"))
        }
        
        _userState.value = _userState.value.copy(isLoading = true, error = null)
        
        return try {
            val response = authRepository.getCurrentUser(token)
            if (response.isSuccessful) {
                val user = response.body()?.user
                if (user != null) {
                    _userState.value = UserState(user = user, isLoading = false)
                    Result.success(user)
                } else {
                    val error = "Invalid response"
                    _userState.value = UserState(isLoading = false, error = error)
                    Result.failure(Exception(error))
                }
            } else {
                val error = "Failed to load user: ${response.code()}"
                _userState.value = UserState(isLoading = false, error = error)
                Result.failure(Exception(error))
            }
        } catch (e: Exception) {
            _userState.value = UserState(isLoading = false, error = e.message)
            Result.failure(e)
        }
    }
    
    /**
     * Update user data locally and sync with API
     * This provides instant UI update + background sync
     */
    suspend fun updateUser(updatedUser: User): Result<User> {
        // 1. Update local state immediately for instant UI update
        _userState.value = _userState.value.copy(user = updatedUser)
        
        // 2. Sync with API in background
        return try {
            // Here you would call API to update user
            // For now just return success since we already updated locally
            Result.success(updatedUser)
        } catch (e: Exception) {
            // If API fails, revert to previous state
            loadUser() // Reload from API
            Result.failure(e)
        }
    }
    
    /**
     * Update only user name (for edit profile)
     */
    suspend fun updateUserName(newName: String): Result<User> {
        val currentUser = _userState.value.user
        if (currentUser == null) {
            return Result.failure(Exception("No user data"))
        }
        
        val updatedUser = currentUser.copy(name = newName)
        return updateUser(updatedUser)
    }
    
    /**
     * Set user data from login/register response
     */
    fun setUser(user: User) {
        _userState.value = UserState(user = user, isLoading = false)
    }
    
    /**
     * Clear user data on logout
     */
    fun clearUser() {
        _userState.value = UserState()
    }
    
    /**
     * Get user data immediately (cached) or null if not available
     */
    fun getUserImmediate(): User? = _userState.value.user
} 