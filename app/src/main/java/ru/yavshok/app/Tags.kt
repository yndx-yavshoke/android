package ru.yavshok.app

object Tags {
    object MainScreen {
        const val screenContainer = "main_screen.container"
        const val screenTitle = "main_screen.screen_title"
        const val emailTextField = "main_screen.email_text_field"
        const val loginButton = "main_screen.login_button"
        const val checkButton = "MainScreenCheckButton"
        const val emailExistTrue = "emailExistTrue"
        const val emailExistFalse = "emailExistFalse"
    }

    object LoginScreen {
        const val screenContainer = "login_screen.container"
        const val screenTitle = "login_screen.screen_title"
        const val emailInput = "emailInput"
        const val passwordInput = "passwordInput"
        const val loginButton = "loginButton"
        const val backButton = "backButton"
        const val registrationButton = "registrationButton"
        const val errorState = "errorState"
    }

    object RegistrationScreen {
        const val screenTitle = "screenTitle"
        const val emailInput = "emailInput"
        const val passwordInput = "passwordInput"
        const val ageInput = "ageInput"
        const val registrationButton = "registrationButton"
        const val backButton = "backButton"
        const val errorState = "errorState"

    }

    object ProfileScreen {
        const val profileAvatar = "profileAvatar"
        const val logoutButton = "logoutButton"
        const val editButton = "editButton"
        const val profileName = "profileName"
    }

    object EditScreen {
        const val screenTitle = "screenTitle"
        const val nameInput = "nameInput"
        const val saveChange = "saveChange"
        const val backButton = "backButton"
        const val errorState = "errorState"
    }
}