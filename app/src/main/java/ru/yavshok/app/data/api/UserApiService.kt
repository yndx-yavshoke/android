package ru.yavshok.app.data.api

import retrofit2.Response
import retrofit2.http.*
import ru.yavshok.app.data.model.UpdateNameRequest
import ru.yavshok.app.data.model.UserResponse

interface UserApiService {
    
    @GET("user/me")
    suspend fun getCurrentUser(): Response<UserResponse>
    
    @PATCH("user/name")
    suspend fun updateUserName(@Body request: UpdateNameRequest): Response<UserResponse>
} 