package ru.yavshok.app.pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import ru.yavshok.app.Tags

class LoginScreenPage (private val composeRule: ComposeTestRule) {
    val title = composeRule.onNodeWithTag("login_screen.screen_title")
    val emailInput = composeRule.onNodeWithTag("login_screen.email_text_field")
    val passwordInput = composeRule.onNodeWithTag("login_screen.password_text_field")
    private val loginButton = composeRule.onNodeWithTag("login_screen.login_button")
    private val backButton = composeRule.onNodeWithTag("login_screen.back_button")
    private val toRegisterScreenButton = composeRule.onNodeWithTag("login_screen.register_button")
    private val errorMessage = composeRule.onNodeWithTag("login_screen.error_message")

    @OptIn(ExperimentalTestApi::class)
    fun waitForTitle(): LoginScreenPage {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )
        return this
    }

    fun fillEmailInput(email: String): LoginScreenPage {
        emailInput.assertIsDisplayed()
        emailInput.performTextInput(email)
        return this
    }

    fun fillPasswordInput(password: String): LoginScreenPage {
        passwordInput.assertIsDisplayed()
        passwordInput.performTextInput(password)
        return this
    }

    fun clickLoginButton(): LoginScreenPage {
        loginButton.assertIsDisplayed()
        loginButton.performClick()
        return this
    }

    fun clickBackButton(): LoginScreenPage {
        backButton.assertIsDisplayed()
        backButton.performClick()
        return this
    }

    fun clickGoToRegisterButton(): LoginScreenPage {
        toRegisterScreenButton.assertIsDisplayed()
        toRegisterScreenButton.performClick()
        return this
    }

    fun fullLogin(email: String, password: String): LoginScreenPage {
        return fillEmailInput(email).fillPasswordInput(password).clickLoginButton()
    }

    fun emptyFieldsError(): LoginScreenPage {
        errorMessage.assertTextContains("Заполните все поля")
        return this
    }

    fun incorrectPasswordOrEmailError (): LoginScreenPage {
        errorMessage.assertTextContains("Неверный email или пароль")
        return this
    }

    fun incorrectFormatOfEmailError (): LoginScreenPage {
        errorMessage.assertTextContains("Неверный формат email")
        return this
    }
}