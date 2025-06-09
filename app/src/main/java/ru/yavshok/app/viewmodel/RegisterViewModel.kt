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

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val age: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isRegistrationSuccessful: Boolean = false,
    val user: User? = null
)

class RegisterViewModel(
    private val authRepository: AuthRepository,
    private val tokenStorage: TokenStorage,
    private val userStore: UserStore
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()
    
    fun updateEmail(email: String) {
        val trimmedEmail = email.trim()
        _uiState.value = _uiState.value.copy(email = trimmedEmail, errorMessage = null)
    }
    
    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password, errorMessage = null)
    }
    
    fun updateAge(age: String) {
        _uiState.value = _uiState.value.copy(age = age, errorMessage = null)
    }
    
    fun register() {
        val currentState = _uiState.value
        
        if (currentState.email.isBlank() || currentState.password.isBlank() || currentState.age.isBlank()) {
            _uiState.value = currentState.copy(errorMessage = "Заполните все поля")
            return
        }
        
        if (!isValidEmail(currentState.email)) {
            _uiState.value = currentState.copy(errorMessage = "Неверный формат email")
            return
        }
        
        if (currentState.email.length > 50) {
            _uiState.value = currentState.copy(errorMessage = "Email не должен превышать 50 символов")
            return
        }
        
        val ageInt = currentState.age.toIntOrNull()
        if (ageInt == null || ageInt < 0 || ageInt > 99) {
            _uiState.value = currentState.copy(errorMessage = "Возраст должен быть от 0 до 99 лет")
            return
        }
        
        if (currentState.password.length < 5 || currentState.password.length > 20) {
            _uiState.value = currentState.copy(errorMessage = "Пароль должен содержать от 5 до 20 символов")
            return
        }
        
        _uiState.value = currentState.copy(isLoading = true, errorMessage = null)
        
        viewModelScope.launch {
            try {
                val response = authRepository.register(currentState.email, currentState.password, ageInt)
                
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    if (registerResponse != null) {
                        // Save token and user data to store
                        tokenStorage.saveToken(registerResponse.token)
                        userStore.setUser(registerResponse.user)
                        
                        _uiState.value = currentState.copy(
                            isLoading = false,
                            isRegistrationSuccessful = true,
                            user = registerResponse.user
                        )
                    } else {
                        _uiState.value = currentState.copy(
                            isLoading = false,
                            errorMessage = "Ошибка сервера"
                        )
                    }
                } else {
                    val errorMessage = when (response.code()) {
                        400 -> "Неверные данные"
                        409 -> "Пользователь с таким email уже существует"
                        422 -> "Пользователь с таким email уже существует"
                        else -> "Ошибка регистрации: ${response.code()}"
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
    
    fun resetRegistrationState() {
        _uiState.value = _uiState.value.copy(
            email = "",
            password = "",
            age = "",
            isLoading = false,
            errorMessage = null,
            isRegistrationSuccessful = false,
            user = null
        )
    }
} 