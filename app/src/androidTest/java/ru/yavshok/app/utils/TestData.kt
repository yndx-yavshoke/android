package ru.yavshok.app.utils

object TestData {
    const val EXISTING_EMAIL = "valerii.mrm@yandex.ru"
    const val INVALID_EMAIL = "invalid-email"
    const val NEW_EMAIL = "newuser@example.com"
    const val VALID_PASSWORD = "qwerty123"
    const val SHORT_PASSWORD = "123"
    const val VALID_AGE = "3"
    const val INVALID_AGE = "many"
    const val DISPLAY_NAME = "Теер"
    const val NEW_NAME = "Тестировщик"
    const val AGE_STATUS = "Взрослый котик"
    const val ERROR_EMAIL_PASSWORD_NOT_EXISTS = "Неверный email или пароль"
    const val ERROR_EMAIL_FORMAT = "Неверный формат email"
    const val ERROR_EMPTY_FIELDS = "Заполните все поля"
    const val ERROR_USER_EXISTS = "Пользователь с таким email уже существует"
    const val ERROR_AGE_FORMAT = "Возраст должен быть от 0 до 99 лет"
    const val ERROR_PASSWORD_SHORT = "Пароль должен содержать от 5 до 20 символов"
}