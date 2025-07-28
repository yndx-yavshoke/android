package ru.yavshok.app

import androidx.compose.ui.platform.testTag

object Tags {
    object MainScreen {
        const val screenContainer = "main_screen.container"
        const val screenTitle = "main_screen.screen_title"
        const val emailTextField = "main_screen.email_text_field"
        const val loginButton = "main_screen.login_button"
        const val checkButton = "main_screen.check_button"
        const val existText = "main_screen.user_exist"
        const val notExistText = "main_screen.user_not_exist"


    }

    object LoginScreen {
        const val screenContainer = "login_screen.container"
        const val screenTitle = "login_screen.screen_title"
        const val emailTextField = "login_screen.email_text_field"
        const val passwordTextField = "login_screen.password_text_field"
        const val loginButton = "login_screen.loginButton"
        const val backButton = "login_screen.backButton"
        const val registerButton = "login_screen.registerButton"
        const val errorText = "login_screen.errorText"

    }

    object RegisterScreen {

        const val screenTitle = "register_screen.screen_title"
        const val emailTextField = "register_screen.email_text_field"
        const val passwordTextField = "register_screen.password_text_field"
        const val ageTextField = "register_screen.age_text_field"
        const val registerButton = "register_screen.registerButton"
        const val backButton = "register_screen.backButton"
        const val errorText = "register_screen.errorText"
    }

    object ProfileScreen {
        const val nameField = "profile_screen.nameField"
        const val statusField = "profile_screen.statusField"
        const val renameButton = "profile_screen.renameButton"
        const val logoutButton = "profile_screen.logoutButton"
    }

    object EditProfileScreen {
        const val screenTitle = "edit_profile_screen.screen_title"
        const val nameField = "edit_profile_screen.nameField"
        const val SaveChangesButton = "edit_profile_screen.SaveChangesButton"
        const val CancelButton = "edit_profile_screen.CancelButton"
        const val ErrorText = "edit_profile_screen.ErrorText"

    }
}