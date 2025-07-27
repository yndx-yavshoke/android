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
import org.junit.runner.RunWith
import org.junit.Test
import ru.yavshok.app.TestData.validUser

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    // Tags.MainScreen.screenTitle
    // Tags.MainScreen.loginButton
    // Tags.LoginScreen.screenTitle
    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shouldNavigateFromMainScreenToLoginScreen() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )

        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertDoesNotExist()

    }

    @Test
    fun shouldNavigateFromLoginScreenToRegistrationScreen() {
        fun openLoginScreen() {
            composeRule.waitUntilAtLeastOneExists(
                timeoutMillis = 10_000L,
                matcher = hasTestTag(Tags.MainScreen.screenTitle)
            )
            composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        }

        openLoginScreen()
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.registrationButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.registrationButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle)
        )

        composeRule.onNodeWithTag(Tags.RegisterScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()

    }

    @Test
    fun shouldNavigateFromRegisterScreenToLoginScreenBack() {
        fun openRegisterScreen() {
            composeRule.waitUntilAtLeastOneExists(
                timeoutMillis = 10_000L,
                matcher = hasTestTag(Tags.MainScreen.screenTitle)
            )
            composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
            composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.LoginScreen.registrationButton).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.LoginScreen.registrationButton).performClick()
            composeRule.waitUntilAtLeastOneExists(
                timeoutMillis = 10_000L,
                matcher = hasTestTag(Tags.RegisterScreen.screenTitle)
            )
        }

        openRegisterScreen()
        composeRule.onNodeWithTag(Tags.RegisterScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.backFromRegistrationButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.backFromRegistrationButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )

        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.screenTitle).assertDoesNotExist()

    }

    @Test
    fun shouldNavigateFromLoginScreenToProfileScreen() {
        fun openLoginScreen() {
            composeRule.waitUntilAtLeastOneExists(
                timeoutMillis = 10_000L,
                matcher = hasTestTag(Tags.MainScreen.screenTitle)
            )
            composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        }

        openLoginScreen()
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).performTextInput(validUser.email)
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).performTextInput(validUser.password)
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(Tags.ProfileScreen.editProfileButton)
        )
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()

    }
}