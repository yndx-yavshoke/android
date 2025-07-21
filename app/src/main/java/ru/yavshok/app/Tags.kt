package ru.yavshok.app

object Tags {
    object MainScreen {
        const val screenContainer = "main_screen.container"
        const val screenTitle = "main_screen.screen_title"
        const val emailTextField = "main_screen.email_text_field"
        const val loginButton = "main_screen.login_button"
        const val statusButton = "main_screen.status_button"
        const val nonRegisterText = "main_screen.nonRegister_text"
    }

    object LoginScreen {
        const val screenContainer = "login_screen.container"
        const val screenTitle = "login_screen.screen_title"
        const val emailTextField = "login_screen.email_text_field"
        const val passwordTextField = "login_screen.password_text_field"
        const val loginButton = "login_screen.login_button"
        const val backButton = "login_screen.back_button"
        const val registerButton = "login_screen.register_button"
        const val errorEmailOrPasswordText = "login_screen.error_email_or_password_text"
    }
    object RegisterScreen {
        const val screenTitle = "register_screen.screen_title"
        const val emailTextField = "register_screen.email_text_field"
        const val passwordTextField = "register_screen.password_text_field"
        const val ageTextField = "register_screen.age_text_field"
        const val backButton = "register_screen.back_button"
        const val errorIfFillNoneOfTheFields = "register_screen.error_none_fields_filled_text"
        const val registerButton = "register_screen.register_button"

    }
    object ProfileScreen {
        const val screenContainer = "profile_screen.container"
        const val subTitle = "profile_screen.sub_title"
        const val userNickname = "profile_screen.screen_title"
        const val logoutButton = "profile_screen.logout_button"
        const val editProfileButton = "profile_screen.edit_profile_button"
        const val profileImage = "profile_screen.profile_image"
    }
    object EditScreen {
        const val screenContainer = "edit_screen.container"
        const val screenTitle = "edit_screen.screen_title"
        const val backButton = "edit_screen.back_button"
        const val nameTextField = "edit_screen.name_text_field"

    }
}