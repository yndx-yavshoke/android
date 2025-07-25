package ru.yavshok.app.pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags
import ru.yavshok.app.data.Data
import ru.yavshok.app.data.Timeouts

@OptIn(ExperimentalTestApi::class)
class LoginPage(private val composeRule: ComposeTestRule) {

    val title = composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle)
    val emailField = composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
    val passwordField = composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField)
    val errorMessage = composeRule.onNodeWithTag(Tags.LoginScreen.errorMessage)
    val loginButton = composeRule.onNodeWithTag(Tags.LoginScreen.loginButton)
    val backButton = composeRule.onNodeWithTag(Tags.LoginScreen.backButton)
    val registerButton = composeRule.onNodeWithTag(Tags.LoginScreen.registerButton)

    fun shouldAuthorizeUser() {
        with(emailField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.EXIST_EMAIL)
        }

        with(passwordField) {
            assertIsDisplayed()
            performTextInput(Data.PASSWORD)
        }

        with(loginButton) {
            assertIsDisplayed()
            performClick()
        }
    }

    fun shouldNotAuthorizeUnregisteredUser() {
        with(emailField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.NON_EXIST_EMAIL)
        }

        with(passwordField) {
            assertIsDisplayed()
            performTextInput(Data.PASSWORD)
        }

        with(loginButton) {
            assertIsDisplayed()
            performClick()
        }

        composeRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.LoginScreen.errorMessage),
            Timeouts.SHORT
        )
        errorMessage.assertIsDisplayed()
    }

    fun shouldNotAuthorizeWrongPassword() {
        with(emailField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.EXIST_EMAIL)
        }

        with(passwordField) {
            assertIsDisplayed()
            performTextInput(Data.WRONG_PASSWORD)
        }

        with(loginButton) {
            assertIsDisplayed()
            performClick()
        }

        composeRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.LoginScreen.errorMessage),
            Timeouts.SHORT
        )
        errorMessage.assertIsDisplayed()
    }

    fun goBackToMainScreen() {
        with(backButton) {
            assertIsDisplayed()
            performClick()
        }
    }

    fun goToRegister() {
        with(registerButton) {
            assertIsDisplayed()
            performClick()
        }
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitExists() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = Timeouts.SHORT,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )
    }
}
