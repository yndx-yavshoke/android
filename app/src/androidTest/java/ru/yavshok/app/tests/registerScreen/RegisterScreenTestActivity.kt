package ru.yavshok.app.tests.registerScreen

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.MainActivity
import ru.yavshok.app.fixtures.LoginScreenPageObj
import ru.yavshok.app.fixtures.MainScreenPageObj
import ru.yavshok.app.fixtures.RegisterScreenPageObj

class RegisterScreenTestActivity {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shouldRedirectBackToLogin() {
        MainScreenPageObj(composeRule)
            .waitForScreen()
            .clickOnToLoginButton()

        LoginScreenPageObj(composeRule)
            .waitForScreen()
            .clickOnToRegisterButton()

        RegisterScreenPageObj(composeRule)
            .waitForScreen()
            .clickOnBackToLoginButton()

        LoginScreenPageObj(composeRule)
            .waitForScreen()

    }

}