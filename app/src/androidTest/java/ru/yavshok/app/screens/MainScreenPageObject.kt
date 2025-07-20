package ru.yavshok.app.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

class MainScreenPageObject(private val composeTestRule: AndroidComposeTestRule<*, *>) {
    public val title = composeTestRule.onNodeWithTag("main_screen.screen_title")
    public val emailField = composeTestRule.onNodeWithTag("main_screen.email_text_field",)
    public val checkButton = composeTestRule.onNodeWithTag("main_screen.check_button")
    public val toLoginButton = composeTestRule.onNodeWithTag("main_screen.login_button")
    public val success = composeTestRule.onNodeWithTag("main_screen.success_message")
    public val fail = composeTestRule.onNodeWithTag("main_screen.failure_message")

    fun enterEmail(email: String): MainScreenPageObject {
        emailField.assertIsDisplayed()
        emailField.performTextInput(email)
        return this
    }

    fun clickCheck(): MainScreenPageObject {
        checkButton.assertIsDisplayed()
        checkButton.performClick()
        return this
    }

    fun clickToLogin(): MainScreenPageObject {
        toLoginButton.assertIsDisplayed()
        toLoginButton.performClick()
        return this
    }

    fun checkEmail(email: String): MainScreenPageObject {
        return enterEmail(email).clickCheck()
    }
}