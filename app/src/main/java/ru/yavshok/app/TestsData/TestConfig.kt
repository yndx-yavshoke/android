package ru.yavshok.app.testdata

object TestConfig {
    // Тестовые данные
    object Credentials {
        const val REGISTER_EMAIL = "test200@yandex.ru"
        const val REGISTER_PASSWORD = "qwerty2025"
        const val TEST_EMAIL = "test@yandex.ru"
        const val INVALID_EMAIL = "invalid@yandex.ru"
        const val PASSWORD = "Qwerty123!"
        const val AGE = "25"
        const val INVALID_AGE = "-1"
    }

    // Тексты UI
    object Texts {
        const val REGISTER_SCREEN_TITLE = "Регистрация в ШОКе"
        const val REGISTER_BUTTON = "Зарегистрироваться"
        const val BACK_BUTTON = "Назад"
        const val EMAIL_LABEL = "Email"
        const val EMAIL_PLACEHOLDER = "Введите email"
        const val PASSWORD_LABEL = "Пароль"
        const val PASSWORD_PLACEHOLDER = "Введите пароль"
        const val AGE_LABEL = "Возраст"
        const val AGE_PLACEHOLDER = "Введите возраст"
    }



    // Ошибки 
    object Errors {
        const val EMPTY_FIELD_EMAIL = "Введите email"
        const val EMPTY_FIELD_PASSWORD = "Введите пароль"
        const val INVALID_FORMAT = "Возраст должен быть числом"
    }
}