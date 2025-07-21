package ru.yavshok.app.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class RegisterScreenPageObject(composeTestRule: AndroidComposeTestRule<*, *>) {
    val title = composeTestRule.onNodeWithTag(Tags.RegisterScreen.screenTitle)
    val emailField = composeTestRule.onNodeWithTag(Tags.RegisterScreen.emailTextField)
    val passwordField = composeTestRule.onNodeWithTag(Tags.RegisterScreen.passwordTextField)
    val ageField = composeTestRule.onNodeWithTag(Tags.RegisterScreen.ageTextField)
    val registerButton = composeTestRule.onNodeWithTag(Tags.RegisterScreen.registerButton)
    val backButton = composeTestRule.onNodeWithTag(Tags.RegisterScreen.backButton)

    fun enterEmail(email: String): RegisterScreenPageObject {
        emailField.assertIsDisplayed()
        emailField.performTextInput(email)
        return this
    }

    fun enterPassword(password: String): RegisterScreenPageObject {
        passwordField.assertIsDisplayed()
        passwordField.performTextInput(password)
        return this
    }

    fun enterAge(age: String): RegisterScreenPageObject {
        ageField.assertIsDisplayed()
        ageField.performTextInput(age)
        return this
    }

    fun clickRegister(): RegisterScreenPageObject {
        registerButton.assertIsDisplayed()
        registerButton.performClick()
        return this
    }

    fun clickBackButton(): RegisterScreenPageObject {
        backButton.assertIsDisplayed()
        backButton.performClick()
        return this
    }

    fun register(email: String, password: String, age: String): RegisterScreenPageObject {
        return enterEmail(email).enterPassword(password).enterAge(age).clickRegister()
    }
}