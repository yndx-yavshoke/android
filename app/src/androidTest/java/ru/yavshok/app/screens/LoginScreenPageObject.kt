package ru.yavshok.app.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class LoginScreenPageObject(composeTestRule: AndroidComposeTestRule<*, *>) {
    val title = composeTestRule.onNodeWithTag(Tags.LoginScreen.screenTitle)
    val emailField = composeTestRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
    val passwordField = composeTestRule.onNodeWithTag(Tags.LoginScreen.passwordTextField)
    val backButton = composeTestRule.onNodeWithTag(Tags.LoginScreen.backButton)
    val loginButton = composeTestRule.onNodeWithTag(Tags.LoginScreen.loginButton)
    val registerButton = composeTestRule.onNodeWithTag(Tags.LoginScreen.registerButton)

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