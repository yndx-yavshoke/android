package ru.yavshok.app.data.model

data class User(
    val id: Int,
    val email: String,
    val name: String,
    val age: Int? = null
) 