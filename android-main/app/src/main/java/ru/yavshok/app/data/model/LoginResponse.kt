package ru.yavshok.app.data.model

data class LoginResponse(
    val token: String,
    val user: User
) 