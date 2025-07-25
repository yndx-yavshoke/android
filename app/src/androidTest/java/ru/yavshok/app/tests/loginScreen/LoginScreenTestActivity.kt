package ru.yavshok.app.tests.loginScreen

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.MainActivity
import ru.yavshok.app.fixtures.LoginScreenPageObj
import ru.yavshok.app.fixtures.MainScreenPageObj


class LoginScreenTestActivity {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shouldRedirectBackToMain() {
        MainScreenPageObj(composeRule)
            .waitForScreen()
            .clickOnToLoginButton()

        LoginScreenPageObj(composeRule)
            .waitForScreen()
            .clickOnBackButton()

        MainScreenPageObj(composeRule)
            .waitForScreen()

    }

}