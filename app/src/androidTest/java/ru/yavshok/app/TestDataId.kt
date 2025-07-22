package ru.yavshok.app

object TestDataId {
    val registeredUser = TestUserData(
        email = "test@yandex.ru",
        password = "test123",
        age = 30
    )
    val nonRegisteredUser = TestUserData (
        email = "notexistsemail@yandex.ru",
        password = "WrongPassword",
        age = 30
    )
}

