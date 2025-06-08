package ru.yavshok.app.data.model

data class RegisterResponse(
    val message: String,
    val token: String,
    val user: User
) 