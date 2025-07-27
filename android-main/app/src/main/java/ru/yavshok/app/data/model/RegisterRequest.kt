package ru.yavshok.app.data.model

data class RegisterRequest(
    val email: String,
    val password: String,
    val age: Int
) 