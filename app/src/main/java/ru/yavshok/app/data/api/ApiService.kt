package ru.yavshok.app.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.yavshok.app.data.model.ExistRequest
import ru.yavshok.app.data.model.ExistResponse
import ru.yavshok.app.data.model.LoginRequest
import ru.yavshok.app.data.model.LoginResponse
import ru.yavshok.app.data.model.RegisterRequest
import ru.yavshok.app.data.model.RegisterResponse
import ru.yavshok.app.data.model.User

interface ApiService {
    @POST("/exist")
    suspend fun checkEmailExists(@Body request: ExistRequest): Response<ExistResponse>
    
    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
    
    @POST("/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
    
    @GET("/user/me")
    suspend fun getCurrentUser(@Header("Authorization") token: String): Response<UserResponse>
}

data class UserResponse(
    val user: User
)