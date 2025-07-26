package ru.yavshok.app.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class LoginScreenPageObject(private val testRule: ComposeTestRule) {
    private val title = testRule.onNodeWithTag(Tags.LoginScreen.screenTitle)
    private val emailField = testRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
    private val passwordField = testRule.onNodeWithTag(Tags.LoginScreen.passwordTextField)
    private val backButton = testRule.onNodeWithTag(Tags.LoginScreen.backButton)
    private val loginButton = testRule.onNodeWithTag(Tags.LoginScreen.loginButton)
    private val registerButton = testRule.onNodeWithTag(Tags.LoginScreen.registerButton)

    fun enterEmail(email: String): LoginScreenPageObject {
        emailField.assertIsDisplayed()
        emailField.performTextInput(email)
        return this
    }

    fun enterPassword(password: String): LoginScreenPageObject {
        passwordField.assertIsDisplayed()
        passwordField.performTextInput(password)
        return this
    }

    fun clickLLogin(): LoginScreenPageObject {
        loginButton.assertIsDisplayed()
        loginButton.performClick()
        return this
    }

    fun clickBack(): LoginScreenPageObject {
        backButton.assertIsDisplayed()
        backButton.performClick()
        return this
    }

    fun clickRegister(): LoginScreenPageObject {
        registerButton.assertIsDisplayed()
        registerButton.performClick()
        return this
    }

    fun loginShock(email: String, password: String): LoginScreenPageObject {
        return enterEmail(email).enterPassword(password).clickLLogin()
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitErrorMessage(message: String): LoginScreenPageObject {
        testRule.waitUntilAtLeastOneExists(
            hasTextExactly(message),
            5000
        )
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitScreenTitle(): LoginScreenPageObject {
        testRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.LoginScreen.screenTitle),
            5000
        )
        return this
    }

    fun assertErrorMessage(message: String): LoginScreenPageObject {
        testRule.onNodeWithText(message)
            .assertIsDisplayed()
        return this
    }

    fun assertTitle(): LoginScreenPageObject {
        title
            .assertIsDisplayed()
            .assertTextContains("Войти в ШОК")
        return this
    }

    fun assertEmailField(): LoginScreenPageObject {
        emailField
            .assertIsDisplayed()
            .assertTextContains("Email")
        return this
    }

    fun assertPasswordField(): LoginScreenPageObject {
        passwordField
            .assertIsDisplayed()
            .assertTextContains("Пароль")
        return this
    }

    fun assertLoginButton(): LoginScreenPageObject {
        loginButton
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertTextContains("В шок")
        return this
    }

    fun assertRegisterButton(): LoginScreenPageObject {
        registerButton
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertTextContains("Регистрация")
        return this
    }

    fun assertBackButton(): LoginScreenPageObject {
        backButton
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertTextContains("Назад")
        return this
    }

}