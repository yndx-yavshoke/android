package ru.yavshok.app.fixtures.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class LoginScreenPage (private val composeRule: ComposeTestRule) {
    val title = composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle)
    val emailTextField = composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
    val passwordTextField = composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField)
    val loginButton = composeRule.onNodeWithTag(Tags.LoginScreen.loginButton)
    val backButton = composeRule.onNodeWithTag(Tags.LoginScreen.backButton)
    val registerButton = composeRule.onNodeWithTag(Tags.LoginScreen.registerButton)
    val errorMessage = composeRule.onNodeWithTag(Tags.LoginScreen.errorMessage)

    fun checkTitleIsDisplayed(): LoginScreenPage {
        title.assertIsDisplayed()
        return this
    }

    fun checkTitleTextIsDisplayed(expected: String = "Войти в ШОК"): LoginScreenPage {
        title.assertTextContains(expected)
        return this
    }

    fun checkEmailPlaceholderIsDisplayed(): LoginScreenPage {
        emailTextField.assertIsDisplayed()
        emailTextField.assertTextContains("Email")
        return this
    }

    fun checkPasswordPlaceholderIsDisplayed(): LoginScreenPage {
        passwordTextField.assertIsDisplayed()
        passwordTextField.assertTextContains("Пароль")
        return this
    }

    fun checkLoginButtonIsDisplayed(): LoginScreenPage {
        loginButton.assertIsDisplayed()
        loginButton.assertTextContains("В шок")
        return this
    }

    fun checkBackButtonIsDisplayed(): LoginScreenPage {
        backButton.assertIsDisplayed()
        backButton.assertTextContains("Назад")
        return this
    }

    fun checkRegisterButtonIsDisplayed(): LoginScreenPage {
        registerButton.assertIsDisplayed()
        registerButton.assertTextContains("Регистрация")
        return this
    }

    fun checkErrorText(expected: String): LoginScreenPage {
        errorMessage.assertIsDisplayed()
        errorMessage.assertTextContains(expected)
        return this
    }

    fun typeEmail(email: String) : LoginScreenPage {
        emailTextField.performTextInput(email)
        return this
    }

    fun typePassword(password: String) : LoginScreenPage {
        passwordTextField.performTextInput(password)
        return this
    }

    fun clickLogin() : LoginScreenPage {
        loginButton.performClick()
        return this
    }

    fun clickBack() : LoginScreenPage {
        backButton.performClick()
        return this
    }

    fun clickRegister() : LoginScreenPage {
        registerButton.performClick()
        return this
    }

    fun login(email: String, password: String) : LoginScreenPage {
        return typeEmail(email).typePassword(password).clickLogin()
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitExistTitle(): LoginScreenPage {
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.LoginScreen.screenTitle),
            timeoutMillis = 5_000L
        )
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitErrorMessage(): LoginScreenPage {
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.LoginScreen.errorMessage),
            timeoutMillis = 5_000L
        )
        return this
    }
}