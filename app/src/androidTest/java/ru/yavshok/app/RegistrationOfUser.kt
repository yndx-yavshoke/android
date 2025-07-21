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
class RegistrationOfUser {
    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun registration() {
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
        composeRule.onNodeWithTag(Tags.RegisterScreen.emailTextField).performTextInput("asdbb@gmail.com")
        composeRule.onNodeWithTag(Tags.RegisterScreen.passwordTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.passwordTextField).assertIsEnabled()
        composeRule.onNodeWithTag(Tags.RegisterScreen.passwordTextField).performTextInput("asdasd")
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageTextField).assertIsEnabled()
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageTextField).performTextInput("20")
        composeRule.onNodeWithTag(Tags.RegisterScreen.registerButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.registerButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.ProfileScreen.userNickname)
        )
        composeRule.onNodeWithTag(Tags.RegisterScreen.screenTitle).assertDoesNotExist()


}
    }