package ru.yavshok.app


object Tags {
    object MainScreen {
        const val screenContainer = "main_screen.container"
        const val screenTitle = "main_screen.screen_title"
        const val emailTextField = "main_screen.email_text_field"
        const val loginButton = "main_screen.login_button"
        const val checkButton = "main_screen.check_button"

       const val successText = "main_screen.success_text"
         const val happyCatImg = "main_screen.happy_cat"

        const val unsuccessText = "main_screen.unsuccess_text"
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

        const val screenTitle = "register_screen.screen_title"

        const val emailTextField = "register_screen.email_text_field"

        const val passwordTextField = "register_screen.password_text_field"

        const val ageTextField = "register_screen.age_text_field"

        const val registerButton = "register_screen.register_button"

        const val backButton = "register_screen.back_button"

        const val errorMessage = "register_screen.error_message"
    }

    object ProfileScreen {
        const val userName = "profile_screen.user_name"

        const val userStatus = "profile_screen.user_status"

        const val editProfileButton = "profile_screen.edit_profile_button"

        const val logoutButton = "profile_screen.logout_button"
    }

    object EditProfileScreen {
        const val screenTitle = "edit_profile_screen.screen_title"

        const val nameLabel = "edit_profile_screen.name_label"

        const val nameTextField = "edit_profile_screen.name_text_field"

        const val errorMessage = "edit_profile_screen.error_message"

        const val saveChangesButton = "edit_profile_screen.save_changes_button"

        const val cancelButton = "edit_profile_screen.cancel_button"


    }
}