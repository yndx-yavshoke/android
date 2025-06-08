package ru.yavshok.app.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.yavshok.app.data.network.NetworkModule
import ru.yavshok.app.data.repository.AuthRepository

class MainViewModel : ViewModel() {

    private val repository = AuthRepository(NetworkModule.provideApiService())

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private val _isEmailExists = mutableStateOf(false)
    val isEmailExists: State<Boolean> = _isEmailExists

    private val _showConfetti = mutableStateOf(false)
    val showConfetti: State<Boolean> = _showConfetti

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        _errorMessage.value = null
        _isEmailExists.value = false
        _showConfetti.value = false
    }

    fun checkEmailExists() {
        if (_email.value.isBlank()) {
            _errorMessage.value = "Введите email"
            return
        }

        if (!isValidEmail(_email.value)) {
            _errorMessage.value = "Неверный формат email"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            _isEmailExists.value = false
            _showConfetti.value = false

            try {
                val response = repository.checkEmailExists(_email.value)
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result?.exist == true) {
                        _isEmailExists.value = true
                        _showConfetti.value = true
                        _errorMessage.value = null
                    } else {
                        _errorMessage.value = "Ты еще не в ШОКе"
                    }
                } else {
                    _errorMessage.value = "Ошибка проверки email"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка соединения: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun hideConfetti() {
        _showConfetti.value = false
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isEmailValid(): Boolean {
        return _email.value.isNotBlank() && isValidEmail(_email.value)
    }
}