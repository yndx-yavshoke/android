package ru.yavshok.app.screens.login

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags
import ru.yavshok.app.testdata.TestConfig

class LoginScreenPage(private val composeTestRule: ComposeTestRule) {

    // Основные элементы
    private val screen = composeTestRule.onNodeWithTag(Tags.LoginScreen.screenContainer)
    private val title = composeTestRule.onNodeWithTag(Tags.LoginScreen.screenTitle)
    private val emailField = composeTestRule.onNodeWithText(TestConfig.EMAIL_PLACEHOLDER)
    private val passwordField = composeTestRule.onNodeWithText(TestConfig.PASSWORD_PLACEHOLDER)
    private val loginButton = composeTestRule.onNodeWithText(TestConfig.Texts.LOGIN_BUTTON)
    private val backButton = composeTestRule.onNodeWithText(TestConfig.Texts.BACK_BUTTON)
    private val registerButton = composeTestRule.onNodeWithText(TestConfig.Texts.REGISTER_BUTTON)

    // Методы проверки
    fun verifyAllElementsVisible() {
        screen.assertExists()
        title.assertExists().assertTextEquals(TestConfig.Texts.LOGIN_SCREEN_TITLE)
        emailField.assertExists()
        passwordField.assertExists()
        loginButton.assertExists()
        backButton.assertExists()
        registerButton.assertExists()
    }

    fun enterEmail(email: String = TestConfig.Credentials.VALID_EMAIL) {
        emailField.performTextInput(email)
    }

    fun enterPassword(password: String = TestConfig.Credentials.PASSWORD) {
        passwordField.performTextInput(password)
    }

    fun clickLoginButton() {
        loginButton.performClick()
    }

    fun clickBackButton() {
        backButton.performClick()
    }

    fun clickRegisterButton() {
        registerButton.performClick()
    }

    fun verifyLoadingIndicatorVisible() {
        composeTestRule.onNodeWithTag(Tags.LoginScreen.loadingIndicator).assertExists()
    }

    fun verifyErrorMessageVisible(message: String = TestConfig.Messages.ERROR) {
        composeTestRule.onNodeWithText(message).assertExists()
    }

    fun verifyEmptyEmailError() {
        composeTestRule.onNodeWithText(TestConfig.Errors.EMPTY_FIELD_EMAIL).assertExists()
    }

    fun verifyEmptyPasswordError() {
        composeTestRule.onNodeWithText(TestConfig.Errors.EMPTY_FIELD_PASSWORD).assertExists()
    }

    fun verifyWrongCredentialsError() {
        composeTestRule.onNodeWithText(TestConfig.Errors.WRONG_CREDENTIALS).assertExists()
    }
}