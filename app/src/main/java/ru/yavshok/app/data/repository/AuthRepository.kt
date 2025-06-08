package ru.yavshok.app.data.repository

import retrofit2.Response
import ru.yavshok.app.data.api.ApiService
import ru.yavshok.app.data.model.ExistRequest
import ru.yavshok.app.data.model.ExistResponse
import ru.yavshok.app.data.model.LoginRequest
import ru.yavshok.app.data.model.LoginResponse
import ru.yavshok.app.data.model.RegisterRequest
import ru.yavshok.app.data.model.RegisterResponse
import ru.yavshok.app.data.api.UserResponse

class AuthRepository(private val apiService: ApiService) {
    suspend fun checkEmailExists(email: String): Response<ExistResponse> {
        return apiService.checkEmailExists(ExistRequest(email))
    }

    suspend fun login(email: String, password: String): Response<LoginResponse> {
        return apiService.login(LoginRequest(email, password))
    }
    
    suspend fun register(email: String, password: String, age: Int): Response<RegisterResponse> {
        return apiService.register(RegisterRequest(email, password, age))
    }
    
    suspend fun getCurrentUser(token: String): Response<UserResponse> {
        return apiService.getCurrentUser("Bearer $token")
    }
}