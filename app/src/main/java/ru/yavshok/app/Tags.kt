package ru.yavshok.app

object Tags {
    object MainScreen {
        const val screenContainer = "main_screen.container"
        const val screenTitle = "main_screen.screen_title"
        const val emailTextField = "main_screen.email_text_field"
        const val loginButton = "main_screen.login_button"
        const val checkButton = "main_screen.check_button"
        const val notEmailExistsTxt = "main_screen.unsuccess_txt"
        const val EmailExistsIMG = "main_screen.success_img"
        const val EmailExistsTxt = "main_screen.unsuccess_txt"
    }

    object LoginPage {
        const val screenContainer = "login_page.container"
        const val screenTitle = "login_page.screen_title"

        const val emailTxtField = "login_page.email_txt_field"

        const val passwordTxtField = "login_page.password_txt_field"

        const val loginButton = "login_page.login_button"

        const val backButton = "login_page.back_button"

        const val registerButton = "login_page.register_button"

        const val errorMessage = "login_page.error_message"
    }

    object RegisterPage {
        const val screenTitle = "register_page.screen_title"

        const val emailTxtField = "register_page.email_txt_field"

        const val passwordTxtField = "register_page.password_txt_field"

        const val ageTxtField = "register_page.age_txt_field"

        const val registerButton = "register_page.register_button"

        const val backButton = "register_page.back_button"

        const val errorMessage = "register_page.error_message"
    }

    object ProfilePage {
        const val userName = "profile_page.user_name"

        const val userStatus = "profile_page.user_status"

        const val editProfileButton = "profile_page.edit_profile_button"

        const val logoutButton = "profile_page.logout_button"
    }

    object EditPage {
        const val screenTitle = "profile_page.screen_title"

        const val nameLabel = "edit_profile_page.name_label"

        const val nameTxtField = "edit_profile_page.name_text_field"

        const val errorMessage = "edit_profile_page.error_message"

        const val saveChangesButton = "edit_profile_page.save_changes_button"

        const val cancelButton = "edit_profile_page.cancel_button"

    }
}