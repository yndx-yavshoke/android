package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.ViewModelFactory

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun backButtonShouldOpenMainScreen() {
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
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithText("Назад").assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.backButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()
    }
    @Test
    fun shouldBeErrorIfUserNonRegister(){
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
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).performTextInput("abc@gmail.com")
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).performTextInput("asdasd")
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
        composeRule.onNodeWithTag(Tags.LoginScreen.errorEmailOrPasswordText).assertIsDisplayed()
    }
    @Test
    fun registerButtonShouldNavigateToRegistration(){
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


    }


}