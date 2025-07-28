package ru.yavshok.app.screens.register

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.testdata.TestConfig

class RegisterScreenPage(private val composeTestRule: ComposeTestRule) {

    // Элементы
    private val title = composeTestRule.onNodeWithText(TestConfig.Texts.REGISTER_SCREEN_TITLE)
    private val emailField = composeTestRule.onNodeWithText(TestConfig.Texts.EMAIL_PLACEHOLDER)
    private val passwordField = composeTestRule.onNodeWithText(TestConfig.Texts.PASSWORD_PLACEHOLDER)
    private val ageField = composeTestRule.onNodeWithText(TestConfig.Texts.AGE_PLACEHOLDER)
    private val registerButton = composeTestRule.onNodeWithText(TestConfig.Texts.REGISTER_BUTTON)
    private val backButton = composeTestRule.onNodeWithText(TestConfig.Texts.BACK_BUTTON)

    // Методы взаимодействия
    fun verifyAllElementsVisible() {
        title.assertExists()
        emailField.assertExists()
        passwordField.assertExists()
        ageField.assertExists()
        registerButton.assertExists()
        backButton.assertExists()
    }

    fun enterRegisterCredentials() {
        enterEmail(TestConfig.Credentials.REGISTER_EMAIL)
        enterPassword(TestConfig.Credentials.REGISTER_PASSWORD)
        enterAge(TestConfig.Credentials.AGE)
    }

    fun enterEmail(email: String = TestConfig.Credentials.TEST_EMAIL) {
        emailField.performTextInput(email)
    }

    fun enterPassword(password: String = TestConfig.Credentials.PASSWORD) {
        passwordField.performTextInput(password)
    }

    fun enterAge(age: String = TestConfig.Credentials.AGE) {
        ageField.performTextInput(age)
    }

    fun clickRegisterButton() {
        registerButton.performClick()
    }

    fun clickBackButton() {
        backButton.performClick()
    }

    fun verifyLoadingVisible() {
        composeTestRule.waitUntil(5000) {
            composeTestRule.onNodeWithText(TestConfig.Messages.LOADING).assertExists()
        }
    }
}