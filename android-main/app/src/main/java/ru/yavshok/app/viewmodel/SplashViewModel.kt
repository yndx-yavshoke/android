package ru.yavshok.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import ru.yavshok.app.data.repository.ExperimentRepository
import ru.yavshok.app.data.repository.AuthRepository
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.data.model.AgeExperiment
import ru.yavshok.app.data.model.AgeGroup
import ru.yavshok.app.data.model.User
import ru.yavshok.app.data.model.determineAgeGroup
import ru.yavshok.app.data.model.getAgeGroupSubtitle

data class SplashUiState(
    val isLoading: Boolean = true,
    val shouldNavigate: Boolean = false,
    val isLoggedIn: Boolean = false
)

class SplashViewModel(
    private val experimentRepository: ExperimentRepository,
    private val tokenStorage: TokenStorage
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()
    
    init {
        loadSplashData()
    }
    
    private fun loadSplashData() {
        viewModelScope.launch {
            try {
                // Check login status
                val isLoggedIn = tokenStorage.isLoggedIn()
                
                // Load experiments (works without login)
                experimentRepository.getAgeExperiment()
                
                // Minimum splash duration
                delay(2000) // 2 seconds minimum
                
                // Navigate to appropriate screen
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    shouldNavigate = true,
                    isLoggedIn = isLoggedIn
                )
                
            } catch (e: Exception) {
                // Handle errors gracefully - still navigate
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    shouldNavigate = true,
                    isLoggedIn = tokenStorage.isLoggedIn()
                )
            }
        }
    }
    

    
    fun resetNavigation() {
        _uiState.value = _uiState.value.copy(shouldNavigate = false)
    }
} 