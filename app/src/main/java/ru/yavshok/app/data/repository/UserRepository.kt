package ru.yavshok.app.data.repository

import retrofit2.Response
import ru.yavshok.app.data.api.UserApiService
import ru.yavshok.app.data.model.UpdateNameRequest
import ru.yavshok.app.data.model.UserResponse

class UserRepository(private val apiService: UserApiService) {
    
    suspend fun getCurrentUser(): Response<UserResponse> {
        return apiService.getCurrentUser()
    }
    
    suspend fun updateUserName(name: String): Response<UserResponse> {
        return apiService.updateUserName(UpdateNameRequest(name))
    }
} 