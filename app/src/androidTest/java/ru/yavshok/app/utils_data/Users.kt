package ru.yavshok.app.utils_data

data class UserFixture(
    val email: String,
    val password: String,
    val age: Int
)

object Users {
    val registeredUser = UserFixture(
        email = "yasssbar@mail.ru",
        password = "1234567",
        age = 15
    )

    val unregisteredUser = UserFixture(
        email = "unregistered_${System.currentTimeMillis()}@mail.ru",
        password = "rghyhggnn",
        age = 43
    )

}