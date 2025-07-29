package ru.yavshok.app.pages

import android.R.string
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.ExperimentalTextApi
import ru.yavshok.app.Tags

@OptIn(ExperimentalTextApi::class)
class LoginScreenPage(private val composeRule: ComposeTestRule) {

    private val screenTitle = Tags.LoginScreen.screenTitle
    private val emailTextField = Tags.LoginScreen.emailTextField
    private val passwordTextField = Tags.LoginScreen.passwordTextField
    private val loginButton = Tags.LoginScreen.loginButton
    private val registerButton = Tags.LoginScreen.registerButton
    private val backButton = Tags.LoginScreen.backButton

    fun displayScreenTitle() = apply {
        composeRule.onNodeWithTag(screenTitle).assertIsDisplayed()
    }

    fun displayEmailTextField() = apply {
        composeRule.onNodeWithTag(emailTextField).assertIsDisplayed()
    }

    fun displayPasswordTextField() = apply {
        composeRule.onNodeWithTag(passwordTextField).assertIsDisplayed()
    }

    fun displayRegisterButton() = apply {
        composeRule.onNodeWithTag(registerButton).assertIsDisplayed()
    }

    fun displayBackButton() = apply {
        composeRule.onNodeWithTag(backButton).assertIsDisplayed()
    }

    fun typeEmail(email: String) = apply {
        composeRule.onNodeWithTag(emailTextField).performTextInput(email)
    }

    fun typePassword(password: String) = apply {
        composeRule.onNodeWithTag(passwordTextField).performTextInput(password)
    }

    fun clickLoginButton() = apply {
        composeRule.onNodeWithTag(loginButton).performClick()
    }

    @OptIn(ExperimentalTestApi::class)
    fun timeoutForScreenTitle() = apply {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(screenTitle)
        )
    }
}