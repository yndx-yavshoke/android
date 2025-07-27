package ru.yavshok.app

import androidx.compose.ui.platform.testTag

object Tags {
    object MainScreen {
        const val screenContainer = "main_screen.container"
        const val screenTitle = "main_screen.screen_title"
        const val emailTextField = "main_screen.email_text_field"
        const val checkEmailButton = "main_screen.check_email_button"
        const val loginButton = "main_screen.login_button"
        const val existEmailText = "main_screen.exist_email_text"
        const val existEmailGif = "main_screen.exist_email_gif"
        const val notExistEmailText = "main_screen.not_exist_email_text"
    }

    object LoginScreen {
        const val screenContainer = "login_screen.container"
        const val screenTitle = "login_screen.screen_title"
        const val emailTextField = "login_screen.email_text_field"
        const val passwordTextField = "login_screen.password_text_field"
        const val loginButton = "login_screen.login_button"
        const val backButton = "login_screen.back_button"
        const val registerButton = "login_screen.register_button"
        const val errorMessage = "login_screen.error_message"
    }

    object RegisterScreen {
        const val screenContainer = "register_screen.container"
        const val screenTitle = "register_screen.screen_title"
        const val emailTextField = "register_screen.email_text_field"
        const val passwordTextField = "register_screen.password_text_field"
        const val ageTextField = "register_screen.age_text_field"
        const val registerButton = "register_screen.register_button"
        const val backButton = "register_screen.back_button"
        const val errorMessage = "register_screen.error_message"
    }

    object ProfileScreen {
        const val screenContainer = "profile_screen.container"
        const val screenContainerHeader = "profile_screen.container_header"
        const val profileImage = "profile_screen.profile_image"
        const val profileName = "profile_screen.profile_name"
        const val editProfileButton = "profile_screen.edit_profile_button"
        const val logoutButton = "profile_screen.logout_button"
    }

    object EditProfileScreen {
        const val screenContainer = "edit_screen.container"
        const val screenTitle = "edit_screen.screen_title"
        const val nameLabel = "edit_screen.name_label"
        const val nameTextField = "edit_screen.name_text_field"
        const val saveChangesButton = "edit_screen.save_changes_button"
        const val cancelButton = "edit_screen.cancel_button"
    }
}