package ru.yavshok.app.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class MainScreenPageObject(private val composeTestRule: AndroidComposeTestRule<*, *>) {
    val title = composeTestRule.onNodeWithTag(Tags.MainScreen.screenTitle)
    val emailField = composeTestRule.onNodeWithTag(Tags.MainScreen.emailTextField)
    val checkButton = composeTestRule.onNodeWithTag(Tags.MainScreen.checkButton)
    val toLoginButton = composeTestRule.onNodeWithTag(Tags.MainScreen.loginButton)
    val success = composeTestRule.onNodeWithTag(Tags.MainScreen.successMessage)
    val fail = composeTestRule.onNodeWithTag(Tags.MainScreen.failureMessage)

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

    fun waitSuccessMessage(): MainScreenPageObject {
        composeTestRule.waitUntil(5000) {
            try {
                success.assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        return this
    }

    fun waitFailMessage(): MainScreenPageObject {
        composeTestRule.waitUntil(5000) {
            try {
                fail.assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        return this
        return this
    }
}