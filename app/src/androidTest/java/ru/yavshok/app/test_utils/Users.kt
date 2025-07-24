package ru.yavshok.app.test_utils

import ru.yavshok.app.BuildConfig

data class UserFixture(
    val email: String,
    val password: String,
    val age: Int
)

object Users {
    val registeredUser = UserFixture(
        email = BuildConfig.TEST_EMAIL,
        password = BuildConfig.TEST_PASSWORD,
        age = BuildConfig.TEST_AGE
        )

    val unregisteredUser = UserFixture(
        email = "unregistered_${System.currentTimeMillis()}@mail.ru",
        password = "somePassword123",
        age = 25
    )

}
