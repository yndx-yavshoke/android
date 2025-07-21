package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
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
class EditScreenTest {
    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun shouldHaveTitle() {
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
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
            .performTextInput("asda@gmail.com")
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).performTextInput("asdasd")
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.ProfileScreen.editProfileButton)
        )
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.EditScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertDoesNotExist()
        composeRule.onNodeWithTag(Tags.EditScreen.screenTitle).assertIsDisplayed()
    }

    @Test
    fun cancelButtonShouldNavigateToProfileScreen() {
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
            composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
                .performTextInput("asda@gmail.com")
            composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).performTextInput("asdasd")
            composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
            composeRule.waitUntilAtLeastOneExists(
                timeoutMillis = 5_000L,
                matcher = hasTestTag(Tags.ProfileScreen.editProfileButton)
            )
            composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()
            composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).performClick()
            composeRule.waitUntilAtLeastOneExists(
                timeoutMillis = 5_000L,
                matcher = hasTestTag(Tags.EditScreen.screenTitle)
            )
            composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertDoesNotExist()
            composeRule.onNodeWithTag(Tags.EditScreen.screenTitle).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.EditScreen.backButton).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.EditScreen.backButton).performClick()
            composeRule.waitUntilAtLeastOneExists(
                timeoutMillis = 5_000L,
             matcher = hasTestTag(Tags.ProfileScreen.editProfileButton)
            )
            composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertIsDisplayed()
            composeRule.onNodeWithTag(Tags.EditScreen.screenTitle).assertDoesNotExist()
    }
    @Test
    fun nameTextFieldShouldBeAvailable() {
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
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
            .performTextInput("asda@gmail.com")
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).performTextInput("asdasd")
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.ProfileScreen.editProfileButton)
        )
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.EditScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertDoesNotExist()
        composeRule.onNodeWithTag(Tags.EditScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.EditScreen.nameTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.EditScreen.screenTitle).assertIsEnabled()
    }
}
