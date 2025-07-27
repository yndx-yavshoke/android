package ru.yavshok.app

object Tags {
    object MainScreen {
        const val screenContainer = "main_screen.container"
        const val screenTitle = "main_screen.screen_title"
        const val emailTextField = "main_screen.email_text_field"
        const val yavshokeButton = "main_screen.ya-v-shoke-button"
        const val loginButton = "main_screen.login_button"
        const val userExistImage = "main_screen.user-exist-avatar"
        const val userExistLabel = "main_screen.user-exist-label"
        const val nonExistUser = "main_screen.user-not-exist-label"
    }

    object LoginScreen {
        const val screenContainer = "login_screen.container"
        const val screenTitle = "login_screen.screen_title"
        const val inputEmail = "login_screen.email-input"
        const val inputPassword = "login_screen.password-input"
        const val loginButton = "login_screen.login-button"
        const val errorMessage = "login_screen.error-message"
        const val backButton = "login_screen.back-button"
        const val registerButton = "login_screen.register-button"
    }

    object RegisterScreen {
        const val screenTitle = "register_screen.screen-title"
        const val inputEmail = "register_screen.input-email"
        const val inputPassword = "register_screen.input-password"
        const val inputAge = "register_screen.input-age"
        const val registerButton = "register_screen.register-button"
        const val errorMessage = "register_screen.error-message"
        const val backButton = "register_screen.back-button"
    }

    object EditProfileScreen {
        const val screenTitle = "edit_profile_screen.screen-title"
        const val nameLabel = "edit_profile_screen.name-label"
        const val inputName = "edit_profile_screen.input-name"
        const val saveChangesButton = "edit_profile_screen.save-changes-button"
        const val cancelButton = "edit_profile_screen.cancel-button"
        const val errorMessage = "edit_profile_screen.error-message"
    }

    object ProfileScreen {
        const val userAvatar = "profile_screen.user-avatar"
        const val userName = "profile_screen.user-name"
        const val userStatus = "profile_screen.user-status"
        const val userStatistics = "profile_screen.user-stats"
        const val userLogoutButton = "profile_screen.user-logout-button"
        const val editProfileButton = "profile_screen.edit-profile-button"
        const val userPhotoGallery = "profile_screen.user-gallery"
    }
}