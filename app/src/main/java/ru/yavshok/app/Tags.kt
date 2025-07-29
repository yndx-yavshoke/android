package ru.yavshok.app

object Tags {
    object MainScreen {
        const val screenContainer = "main_screen.container"
        const val screenTitle = "main_screen.screen_title"
        const val emailTextField = "main_screen.email_text_field"
        const val checkButton = "main_screen.check_button"
        const val loginButton = "main_screen.login_button"
        const val successText = "main_screen.success_message"
        const val errorText = "main_screen.error_message"
    }

    object LoginScreen {
        const val screenContainer = "login_screen.container"
        const val screenTitle = "login_screen.screen_title"
        const val emailTextField = "login_screen.email_text_field"
        const val passwordTextField = "login_screen.password_text_field"
        const val loginButton = "login_screen.login_button"
        const val backButton = "login_screen.back_button"
        const val toRegisterScreenButton = "login_screen.register_button"
        const val errorMessage = "login_screen.error_message"
    }

    object RegisterScreen {
        //const val screenContainer = "login_screen.container"
        const val screenTitle = "register_screen.screen_title"
        const val emailTextField = "register_screen.email_text_field"
        const val passwordTextField = "register_screen.password_text_field"
        const val ageField = "register_screen.age_field"
        const val registerButton = "register_screen.register_button"
        const val backButton = "register_screen.back_button"
        const val errorMessage = "register_screen.error_message"
    }

    object ProfileScreen {
        const val profileImage = "profile_screen.profile_image"
        const val profileUserName = "profile_screen.profile_user_name"
        const val profileAgeStatus = "profile_screen.profile_age_status"
        const val logoutButton = "profile_screen.profile_logout_button"
        const val editButton = "profile_screen.profile_edit_button"
    }

    object EditProfileScreen {
        const val title = "edit_profile_screen.screen_title"
        const val nameEditInput = "edit_profile_screen.name_edit_input"
        const val saveChangesButton = "edit_profile_screen.save_changes_button"
        const val cancelButton = "edit_profile_screen.cancel_button"
    }
}