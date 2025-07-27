package ru.yavshok.app

object Tags {
    object MainScreen {
        const val screenContainer = "main_screen.container"
        const val screenTitle = "main_screen.screen_title"
        const val emailTextField = "main_screen.email_text_field"
        const val loginButton = "main_screen.login_button"
        const val checkButton = "main_screen.check_button"
        const val successText = "SuccessText"
        const val happyGif = "GifHappyCat"
        const val failText = "ErrorText"
    }

    object LoginScreen {
        const val screenContainer = "login_screen.container"
        const val screenTitle = "login_screen.screen_title"
        const val emailTextField = "LoginEmailField"
        const val passwordTextField = "LoginPasswordField"
        const val submitButton = "LoginSubmitButton"
        const val goBackButton = "LoginGoBackButton"
        const val goToRegisterButton = "LoginGoToRegisterButton"
        const val loginError = "LoginErrorMessage"
    }

    object RegisterScreen {
        const val registerScreenTitle = "RegisterScreenTitle"
        const val emailField = "RegisterEmailField"
        const val passwordField = "RegisterPasswordField"
        const val ageField = "RegisterAgeField"
        const val submitButton = "RegisterSubmitButton"
        const val backButton = "RegisterGoBackButton"
        const val errorText = "errorMessage"
        const val loadingIndicator = "RegisterProgressIndicator"
    }

    object ProfileScreen {
        const val userName = "ProfileUsername"
        const val userStatus = "ByAgeStatus"
        const val userAvatar = "ProfileUserAvatar"
        const val logoutButton = "ProfileLogoutButton"
        const val editButton = "ProfileEditButton"
    }


}