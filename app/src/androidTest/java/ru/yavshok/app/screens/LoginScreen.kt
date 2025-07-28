package ru.yavshok.app.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class LoginScreen(private val composeRule: ComposeTestRule) {
    private val screenTitle = Tags.LoginScreen.screenTitle
    private val emailTextField = Tags.LoginScreen.emailTextField
    private val passwordTextField = Tags.LoginScreen.passwordTextField
    private val loginButton = Tags.LoginScreen.loginButton
    private val backButton = Tags.LoginScreen.backButton

    fun waitToLoad() {
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(screenTitle),
            timeoutMillis = 5_000L
        )
    }

    fun checkScreenTitleIsDisplayed() {
        composeRule.onNodeWithTag(screenTitle).assertIsDisplayed()
    }

    fun checkEmailTextFieldIsDisplayed() {
        composeRule.onNodeWithTag(emailTextField).assertIsDisplayed()
    }

    fun checkPasswordTextFieldIsDisplayed() {
        composeRule.onNodeWithTag(passwordTextField).assertIsDisplayed()
    }

    fun checkLoginButtonIsDisplayed() {
        composeRule.onNodeWithTag(loginButton).assertIsDisplayed()
    }

    fun checkRegisterButtonIsDisplayed() {
        composeRule.onNodeWithTag(loginButton).assertIsDisplayed()
    }

    fun checkBackButtonIsDisplayed() {
        composeRule.onNodeWithTag(loginButton).assertIsDisplayed()
    }

    fun enterEmail(email: String) {
        composeRule.onNodeWithTag(emailTextField).performTextInput(email)
    }

    fun enterPassword(password: String) {
        composeRule.onNodeWithTag(passwordTextField).performTextInput(password)
    }

    fun clickLoginButton() {
        composeRule.onNodeWithTag(loginButton).performClick()
    }

    fun clickBackButton() {
        composeRule.onNodeWithTag(backButton).performClick()
    }

    fun assertNotDisplayed() {
        composeRule.onAllNodesWithTag(emailTextField).assertCountEquals(0)
    }

    fun assertEmailDisplayed(email: String) {
        composeRule.onNodeWithTag(emailTextField).assertTextContains(email)
    }
}