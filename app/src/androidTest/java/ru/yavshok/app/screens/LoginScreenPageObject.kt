package ru.yavshok.app.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

class LoginScreenPageObject(private val composeTestRule: AndroidComposeTestRule<*, *>) {
    public val title = composeTestRule.onNodeWithTag("login_screen.screen_title")
    public val emailField = composeTestRule.onNodeWithTag("login_screen.email_text_field",)
    public val passwordField = composeTestRule.onNodeWithTag("login_screen.password_text_field",)
    public val backButton = composeTestRule.onNodeWithTag("login_screen.back_button")
    public val loginButton = composeTestRule.onNodeWithTag("login_screen.login_button")
    public val registerButton = composeTestRule.onNodeWithTag("login_screen.register_button")

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
}