package ru.yavshok.app.pages

import android.nfc.Tag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.*
import androidx.compose.ui.text.ExperimentalTextApi
import ru.yavshok.app.Tags

@OptIn(ExperimentalTextApi::class)
class MainScreenPage(private val composeRule: ComposeTestRule) {
    private val screenTitle = Tags.MainScreen.screenTitle
    private val emailTextField = Tags.MainScreen.emailTextField
    private val loginButton = Tags.MainScreen.loginButton

    fun displayScreenTitle() = apply {
        composeRule.onNodeWithTag(screenTitle).assertIsDisplayed()
    }

    fun displayEmailTextField() = apply {
        composeRule.onNodeWithTag(emailTextField).assertIsDisplayed()
    }

    fun displayLoginButton() = apply {
        composeRule.onNodeWithTag(loginButton).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    fun timeoutForScreenTitle() = apply {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(screenTitle)
        )
    }

    fun clickLoginButton() = apply {
        composeRule.onNodeWithTag(loginButton).performClick()
    }

    fun typeEmail(email: String) = apply {
        composeRule.onNodeWithTag(emailTextField).performTextInput(email)
    }

    fun emailIsContain(email: String) = apply {
        composeRule.onNodeWithTag(emailTextField).assertTextContains(email)
    }
}