package ru.yavshok.app.utility

class ApplicationTextEnv {
    object MainScreen {
        const val title = "Я в ШОКе"
        const val emailButton = "Введите Email"
        const val checkButton = "Я в шоке?"
        const val loginButton = "В шок"
        const val successMessage = "Ты уже в ШОКе"
        const val failedMessage = "Ты еще не в ШОКе"
    }
    
    object LoginScreen {
        const val title = "Войти в ШОК"
        const val emailTextField = "Email"
        const val passwordTextField = "Пароль"
        const val loginButton = "В шок"
        const val registrationButton = "Регистрация"
        const val backButton = "Назад"
    }
    
    object RegistrationScreen {
        const val title = "Регистрация в ШОКе"
        const val emailTextField = "Email"
        const val passwordTextField = "Пароль"
        const val ageTextField = "Возраст"
        const val registrationButton = "Зарегистрироваться"
        const val backButton = "Назад"
    }
    
    object ProfileScreen {
        const val editButton = "Редактировать"
        const val exitButton = "Выйти"
    }
    
    object EditProfileScreen {
        const val title = "Edit Profile"
        const val nameTextField = "Enter your name"
        const val saveButton = "Save Changes"
        const val cancelButton = "Cancel"
    }
}