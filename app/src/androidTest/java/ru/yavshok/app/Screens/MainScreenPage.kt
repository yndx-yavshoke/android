package ru.yavshok.app.Screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import ru.yavshok.app.Tags
import androidx.compose.ui.test.ExperimentalTestApi

@OptIn(ExperimentalTestApi::class)
class MainScreenPage(private val rule: ComposeTestRule) {

    private val title = Tags.MainScreen.screenTitle
    private val emailField = Tags.MainScreen.emailTextField
    private val checkLoginButton = Tags.MainScreen.checkLoginButton
    private val loginButton = Tags.MainScreen.loginButton
    private val checkSuccessLine = Tags.MainScreen.lineCheckEmailTrue
    private val checkFailedLine = Tags.MainScreen.lineCheckEmailFalse

    fun assertTitleIsDisplayed() = apply {
        rule.onNodeWithTag(title).assertIsDisplayed()
    }

    fun assertEmailFieldIsDisplayed() = apply {
        rule.onNodeWithTag(emailField).assertIsDisplayed()
    }

    fun assertCheckLoginButtonIsDisplayed() = apply {
        rule.onNodeWithTag(checkLoginButton).assertIsDisplayed()
    }

    fun assertCheckLoginButtonIsEnabled() = apply {
        rule.onNodeWithTag(checkLoginButton).assertIsEnabled()
    }

    fun assertCheckLoginButtonIsDisabled() = apply {
        rule.onNodeWithTag(checkLoginButton).assertIsNotEnabled()
    }

    fun assertLoginButtonIsDisplayed() = apply {
        rule.onNodeWithTag(loginButton).assertIsDisplayed()
    }

    fun enterEmail(email: String) = apply {
        rule.onNodeWithTag(emailField).performTextInput(email)
    }

    fun clearEmailField() = apply {
        rule.onNodeWithTag(emailField).performTextClearance()
    }

    fun assertEmailFieldHasText(expected: String) = apply {
        rule.onNodeWithTag(emailField).assertTextEquals(expected)
    }

    fun assertEmailPlaceholderDisplayed(placeholder: String) = apply {
        rule.onNodeWithTag(emailField).assert(hasText(placeholder))
    }

    fun clickCheckLoginButton() = apply {
        rule.onNodeWithTag(checkLoginButton).performClick()
    }

    fun clickLoginButton() = apply {
        rule.onNodeWithTag(loginButton).performClick()
    }

    fun assertSuccessEmailCheck() {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(checkSuccessLine)
        )
    }

    fun waitExists() {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(title)
        )
    }

    fun assertFailedEmailCheck() = apply {
        rule.waitUntilExactlyOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(checkFailedLine)
        )
    }

}