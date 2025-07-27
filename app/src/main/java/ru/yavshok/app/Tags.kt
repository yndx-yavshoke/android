package ru.yavshok.app

object Tags {
    object MainScreen {
        const val screenContainer = "main_screen.container"
        const val screenTitle = "main_screen.screen_title"
        const val emailTextField = "main_screen.email_text_field"
        const val checkButton = "main_screen.check_button"
        const val loginButton = "main_screen.login_button"
        const val loginWrongText = "main_screen.login_wrong_text"
        const val loginSuccessText = "main_screen.login_success_text"
    }

    object LoginScreen {
        const val screenContainer = "login_screen.container"
        const val screenTitle = "login_screen.screen_title"
        const val emailTextField = "login_screen.email_text_field"
        const val passwordTextField = "login_screen.password_text_field"
        const val errorMessage= "login_screen.error_message"
        const val loginButton = "login_screen.login_button"
        const val backButton = "login_screen.back_button"
        const val goToRegistrScreenButton = "login_screen.go_to_register_screen_button"
        const val loadingIndicator = "login_screen.loading_indicator"
    }

    object RegisterScreen {
        const val screenTitle = "register_screen.screen_title"
        const val emailTextField = "register_screen.email_text_field"
        const val passwordTextField = "register_screen.password_text_field"
        const val ageTextField = "register_screen.age_text_field"
        const val errorMessage= "register_screen.error_message"
        const val registerButton = "register_screen.register_button"
        const val backButton = "register_screen.back_button"
    }

    object ProfileScreen {
        const val profileImage = "profile_screen.profile_image"
        const val profileName = "profile_screen.profile_name"
        const val profileStatus = "profile_screen.profile.status"
        const val logoutButton = "profile_screen.logout_button"
        const val editProfileButton = "profile_screen.edit_profile_button"
    }

    object EditProfileScreen {
        const val screenTitle = "edit_profile_screen.screen_title"
        const val changeProfileName = "edit_profile_screen.change_profile_name"
        const val errorMessage= "edit_profile_screen.error_message"
        const val saveChangeButton = "edit_profile_screen.save_change_button"
        const val cancelButton = "edit_profile_screen.cancel_button"
    }
}