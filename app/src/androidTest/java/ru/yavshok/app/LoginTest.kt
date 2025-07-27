package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class LoginTest {
    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shouldLoginExistingUser() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.LoginScreen.emailField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.emailField).performTextInput("a@ya.ru")
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordField).performTextInput("123456")
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.ProfileScreen.logoutButton)
        )
        composeRule.onNodeWithTag(Tags.ProfileScreen.logoutButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()
    }

    @Test
    fun shouldNotLoginExistingUserWithWrongPassword() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.LoginScreen.emailField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.emailField).performTextInput("a@ya.ru")
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordField).performTextInput("654321")
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()

        composeRule.onNodeWithTag(Tags.ProfileScreen.logoutButton).assertDoesNotExist()
        composeRule.onNodeWithTag(Tags.LoginScreen.errorMessage).assertIsDisplayed()
    }

}