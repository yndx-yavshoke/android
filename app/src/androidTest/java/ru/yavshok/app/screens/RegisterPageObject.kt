package ru.yavshok.app.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

class RegisterPageObject(private val composeTestRule: AndroidComposeTestRule<*, *>) {
    private val title = composeTestRule.onNodeWithTag("main_screen.screen_title")
    private val emailField = composeTestRule.onNodeWithTag("main_screen.email_text_field",)
    private val checkButton = composeTestRule.onNodeWithTag("main_screen.check_button")
    private val toLoginButton = composeTestRule.onNodeWithTag("main_screen.login_button")
    private val success = composeTestRule.onNodeWithTag("main_screen.success_message")
    private val fail = composeTestRule.onNodeWithTag("main_screen.failure_message")

    fun enterEmail(email: String): RegisterPageObject {
        emailField.assertIsDisplayed()
        emailField.performTextInput(email)
        return this
    }

    fun clickCheck(): RegisterPageObject {
        checkButton.assertIsDisplayed()
        checkButton.performClick()
        return this
    }

    fun clickToLogin(): RegisterPageObject {
        toLoginButton.assertIsDisplayed()
        toLoginButton.performClick()
        return this
    }

    fun checkEmail(email: String): RegisterPageObject {
        return enterEmail(email).clickCheck()
    }

    fun getSuccessMessage() = success
    fun getFailureMessage() = fail
    fun getTitle() = title
    fun getEmail() = emailField
    fun getCheckButton() = checkButton
    fun getToLoginButton() = toLoginButton
}