package ru.yavshok.app

object Tags {
    object MainScreen {
        const val screenContainer = "main_screen.container"
        const val screenTitle = "main_screen.screen_title"
        const val emailTextField = "main_screen.email_text_field"
        const val loginButton = "main_screen.login_button"
    }

    object LoginScreen {
        const val screenContainer = "login_screen.container"
        const val screenTitle = "login_screen.screen_title"
        const val emailTextField = "login_screen.email_text_field"
        const val passwordTextField = "login_screen.password_text_field"
        const val registerButton = "login_screen.register_button"
        const val loginButton = "login_screen.login_button"
        const val backButton = "login_screen.back_button"
    }

    object RegisterScreen {
        const val screenTitle = "register_screen.screen_title"
        const val emailTextField = "register_screen.email_text_field"
        const val passwordTextField = "register_screen.password_text_field"
        const val ageTextField = "register_screen.age_text_field"
        const val registerButton = "register_screen.register_button"
        const val backButton = "register_screen.back_button"
        const val errorMessage = "register_screen.error_message"
    }

    object ProfileScreen {
        const val profileImage = "profile_screen.profile_image"
        const val profileTextName = "profile_screen.profile_text_name"
        const val profileTextStatus = "profile_screen.profile_text_status"
        const val exitButton = "profile_screen.exit_button"
        const val editProfileButton = "profile_screen.edit_profile_button"

    }

    object EditProfileScreen {
        const val screenTitle = "edit_profile_screen.screen_title"
        const val nameTextField = "edit_profile_screen.name_text_field"
        const val saveChangesButton = "edit_profile_screen.save_changes_button"
        const val cancelButton = "edit_profile_screen.cancel_button"
    }
}