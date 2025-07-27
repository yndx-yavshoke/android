package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule

@OptIn(ExperimentalTestApi::class)
open class BaseNavigationTest {

    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    fun waitForMainScreen() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
    }

    fun clickLoginButton() {
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
    }

    fun waitForLoginScreen() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )
    }
}
