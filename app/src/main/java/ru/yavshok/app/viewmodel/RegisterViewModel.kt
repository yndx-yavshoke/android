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
    private val tokenStorage: TokenStorage
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()
    
    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email, errorMessage = null)
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
        
        val ageInt = currentState.age.toIntOrNull()
        if (ageInt == null || ageInt < 18 || ageInt > 100) {
            _uiState.value = currentState.copy(errorMessage = "Возраст должен быть от 18 до 100 лет")
            return
        }
        
        if (currentState.password.length < 6) {
            _uiState.value = currentState.copy(errorMessage = "Пароль должен содержать минимум 6 символов")
            return
        }
        
        _uiState.value = currentState.copy(isLoading = true, errorMessage = null)
        
        viewModelScope.launch {
            try {
                val response = authRepository.register(currentState.email, currentState.password, ageInt)
                
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    if (registerResponse != null) {
                        // Save token and user data
                        tokenStorage.saveToken(registerResponse.token)
                        tokenStorage.saveUser(
                            registerResponse.user.id,
                            registerResponse.user.email,
                            registerResponse.user.name
                        )
                        
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
                        422 -> "Данные не прошли валидацию"
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