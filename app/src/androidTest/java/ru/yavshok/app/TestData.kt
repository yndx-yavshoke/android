package ru.yavshok.app

data class TestUserData (
    val email: String,
    val password: String)


object TestData {
    val validUser = TestUserData(
        email = "kotik@meow.ru",
        password = "kotikitop13"
    )
}