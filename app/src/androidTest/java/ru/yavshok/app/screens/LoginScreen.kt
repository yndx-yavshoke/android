package ru.yavshok.app.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags
import secrets.MyUser

@OptIn(ExperimentalTestApi::class)
class LoginScreen(private val rule: ComposeTestRule) {
    private val title get() = rule.onNodeWithTag(Tags.LoginScreen.screenTitle)
    private val emailField get() = rule.onNodeWithTag(Tags.LoginScreen.emailTextField)
    private val passField get() = rule.onNodeWithTag(Tags.LoginScreen.passTextField)
    private val errorText get() = rule.onNodeWithTag(Tags.LoginScreen.errorText)
    private val loginButton get() = rule.onNodeWithTag(Tags.LoginScreen.loginButton)
    private val backButton get() = rule.onNodeWithTag(Tags.LoginScreen.backButton)
    private val registerButton get() = rule.onNodeWithTag(Tags.LoginScreen.registerButton)

    fun waitExists() {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 5000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )
    }

    fun assertScreenDisplayed() {
        title.assertIsDisplayed()
        emailField.assertIsDisplayed()
        passField.assertIsDisplayed()
        loginButton.assertIsDisplayed()
        backButton.assertIsDisplayed()
        registerButton.assertIsDisplayed()
    }

    fun typeEmail(email: String) {
        emailField.performTextInput(email)
    }

    fun typePassword(pass: String) {
        passField.performTextInput(pass)
    }

    fun clickOnLogin() {
        loginButton.performClick()
    }

    fun clickOnBack() {
        backButton.performClick()
    }

    fun clickOnRegister() {
        registerButton.performClick()
    }

    fun logInWithRegisterUser() {
        emailField.performTextInput(MyUser.EMAIL)
        passField.performTextInput(MyUser.PASSWORD)
        loginButton.performClick()
    }

    fun assertErrorIsInvalidPassword() {
        rule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.LoginScreen.errorText),
            timeoutMillis = 5000L,
        )

        errorText.assertTextEquals("Неверный email или пароль")
        assertScreenDisplayed()
    }

    fun assertErrorIsEmptyFields() {
        rule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.LoginScreen.errorText),
            timeoutMillis = 5000L,
        )

        errorText.assertTextEquals("Заполните все поля")
        assertScreenDisplayed()
    }
}