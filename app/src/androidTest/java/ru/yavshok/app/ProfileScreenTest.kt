package ru.yavshok.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileScreenTest : BaseNavigationTest() {

    fun shouldNavigateToProfile() = with(composeRule) {
        waitForMainScreen()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        clickLoginButton()
        waitForLoginScreen()
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertDoesNotExist()

        composeRule.onNodeWithTag(Tags.LoginScreen.emailInput).performTextInput("test@yavshok.ru")
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordInput).performTextInput("QWErty!1")
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
    }

    @Test
    fun shouldCheckProfileScreenElements() {
        shouldNavigateToProfile()

        composeRule.onNodeWithTag(Tags.ProfileScreen.profileAvatar).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.ProfileScreen.logoutButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.ProfileScreen.editButton).assertIsDisplayed()
    }

    @Test
    fun shouldCheckLogout() {
        shouldNavigateToProfile()

        composeRule.onNodeWithTag(Tags.ProfileScreen.logoutButton).performClick()
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
    }
}