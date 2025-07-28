package ru.yavshok.app.screens

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class MainScreen(private val composeRule: ComposeTestRule) {
    private val screenTitle = Tags.MainScreen.screenTitle
    private val emailTextField = Tags.MainScreen.emailTextField
    private val loginButton = Tags.MainScreen.loginButton
    private val checkButton = Tags.MainScreen.checkLoginButton
    private val successLabel = Tags.MainScreen.successLabel
    private val failureLabel = Tags.MainScreen.failureLabel
    private val successCatImage = Tags.MainScreen.successCatImage

    fun checkEmailFieldIsDisplayed() {
        composeRule.onNodeWithTag(emailTextField).assertIsDisplayed()
    }

    fun checkLoginButtonIsDisplayed() {
        composeRule.onNodeWithTag(loginButton).assertIsDisplayed()
    }

    fun checkCheckButtonIsDisplayed() {
        composeRule.onNodeWithTag(checkButton).assertIsDisplayed()
    }

    fun tapLoginButton()  {
        composeRule.onNodeWithTag(loginButton).performClick()
    }

    fun tapCheckButton() {
        composeRule.onNodeWithTag(checkButton).performClick()
    }

    fun typeEmail(email: String)  {
        composeRule.onNodeWithTag(emailTextField).performTextInput(email)
    }

    fun checkTypedEmail(email: String) {
        composeRule.onNodeWithTag(emailTextField).assertTextContains(email)
    }

    fun checkSuccessLabelIsDisplayed() {
        composeRule.onNodeWithTag(successLabel).assertIsDisplayed()
    }

    fun checkFailureLabelIsDisplayed() {
        composeRule.onNodeWithTag(failureLabel).assertIsDisplayed()
    }

    fun checkSuccessCatIsDisplayed() {
        composeRule.onNodeWithTag(successCatImage).assertIsDisplayed()
    }

    fun waitToLoadLabel() = apply {
        composeRule.waitUntilExactlyOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(successLabel)
        )
    }

    fun waitToLoad() {
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(screenTitle),
            timeoutMillis = 5_000L
        )
    }
}