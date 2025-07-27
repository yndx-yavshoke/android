package ru.yavshok.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yavshok.app.data.repository.AuthRepository
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.data.model.User
import ru.yavshok.app.data.store.UserStore

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoginSuccessful: Boolean = false,
    val user: User? = null
)

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val tokenStorage: TokenStorage,
    private val userStore: UserStore
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()
    
    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email, errorMessage = null)
    }
    
    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password, errorMessage = null)
    }
    
    fun login() {
        val currentState = _uiState.value
        
        if (currentState.email.isBlank() || currentState.password.isBlank()) {
            _uiState.value = currentState.copy(errorMessage = "Заполните все поля")
            return
        }
        
        if (!isValidEmail(currentState.email)) {
            _uiState.value = currentState.copy(errorMessage = "Неверный формат email")
            return
        }
        
        _uiState.value = currentState.copy(isLoading = true, errorMessage = null)
        
        viewModelScope.launch {
            try {
                val response = authRepository.login(currentState.email, currentState.password)
                
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        // Save token and user data to store
                        tokenStorage.saveToken(loginResponse.token)
                        userStore.setUser(loginResponse.user)
                        
                        _uiState.value = currentState.copy(
                            isLoading = false,
                            isLoginSuccessful = true,
                            user = loginResponse.user
                        )
                    } else {
                        _uiState.value = currentState.copy(
                            isLoading = false,
                            errorMessage = "Ошибка сервера"
                        )
                    }
                } else {
                    val errorMessage = when (response.code()) {
                        422 -> "Неверный email или пароль"
                        401 -> "Ошибка авторизации"
                        404 -> "Пользователь не найден"
                        500 -> "Ошибка сервера"
                        else -> "Ошибка входа: ${response.code()}"
                    }
                    _uiState.value = currentState.copy(
                        isLoading = false,
                        errorMessage = errorMessage
                    )
                }
            } catch (e: Exception) {
                _uiState.value = currentState.copy(
                    isLoading = false,
                    errorMessage = "Ошибка сети: ${e.message}"
                )
            }
        }
    }
    
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
    
    fun resetLoginState() {
        _uiState.value = _uiState.value.copy(
            email = "",
            password = "",
            isLoading = false,
            errorMessage = null,
            isLoginSuccessful = false,
            user = null
        )
    }
} 