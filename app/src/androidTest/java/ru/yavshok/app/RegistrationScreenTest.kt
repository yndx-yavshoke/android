package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class RegistrationScreenTest {
    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun allTextInputAreAvailable() {
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
            composeRule.onNodeWithTag(Tags.LoginScreen.registerButton).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.LoginScreen.registerButton).performClick()
            composeRule.waitUntilAtLeastOneExists(
                timeoutMillis = 5_000L,
                matcher = hasTestTag(Tags.RegisterScreen.screenTitle)
            )
            composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()
            composeRule.onNodeWithTag(Tags.RegisterScreen.emailTextField).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.RegisterScreen.emailTextField).assertIsEnabled()
            composeRule.onNodeWithTag(Tags.RegisterScreen.passwordTextField).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.RegisterScreen.passwordTextField).assertIsEnabled()
            composeRule.onNodeWithTag(Tags.RegisterScreen.ageTextField).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.RegisterScreen.ageTextField).assertIsEnabled()
        Thread.sleep(9999)
            }
    @Test
    fun backButtonShouldNavigateToLoginScreen() {
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
        composeRule.onNodeWithTag(Tags.LoginScreen.registerButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.registerButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()
        composeRule.onNodeWithTag(Tags.RegisterScreen.backButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.backButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.RegisterScreen.screenTitle).assertDoesNotExist()
    }
    @Test
    fun errorIfFillNoneOfTheFields() {
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
        composeRule.onNodeWithTag(Tags.LoginScreen.registerButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.registerButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()
        composeRule.onNodeWithTag(Tags.RegisterScreen.registerButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.registerButton).performClick()
        composeRule.onNodeWithTag(Tags.RegisterScreen.errorIfFillNoneOfTheFields).assertIsDisplayed()

    }
    }