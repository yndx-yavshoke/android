package ru.yavshok.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import com.github.javafaker.Faker

class Faker {
    val faker = Faker()
    val fakeEmail = faker.internet().emailAddress()
    val fakePassword = faker.internet().password()
}

@RunWith(AndroidJUnit4::class)
class LoginScreenTest : BaseNavigationTest() {

    fun shouldNavigateToLogin() = with(composeRule) {
        waitForMainScreen()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        clickLoginButton()
        waitForLoginScreen()
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertDoesNotExist()
    }

    @Test
    fun shouldCheckLoginScreenElements() {
        shouldNavigateToLogin()

        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.emailInput).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordInput).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.backButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.registrationButton).assertIsDisplayed()
    }

    @Test
    fun shouldLoginNotExistUser() {
        shouldNavigateToLogin()

        composeRule.onNodeWithTag(Tags.LoginScreen.emailInput).performTextInput(fakeEmail)
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordInput).performTextInput(fakePassword)
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule.onAllNodesWithTag(Tags.LoginScreen.errorState).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag(Tags.LoginScreen.errorState).assertIsDisplayed()
    }

    @Test
    fun shouldLoginWithWrongEmail() {
        shouldNavigateToLogin()

        composeRule.onNodeWithTag(Tags.LoginScreen.emailInput).performTextInput(fakeEmail)
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordInput).performTextInput(fakePassword)
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule.onAllNodesWithTag(Tags.LoginScreen.errorState).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag(Tags.LoginScreen.errorState).assertIsDisplayed()
    }

    @Test
    fun shouldLoginWithEmptyInputs() {
        shouldNavigateToLogin()

        composeRule.onNodeWithTag(Tags.LoginScreen.emailInput).performTextInput("")
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordInput).performTextInput("")
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule.onAllNodesWithTag(Tags.LoginScreen.errorState).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag(Tags.LoginScreen.errorState).assertIsDisplayed()
    }

    @Test
    fun shouldLoginExistUser() {
        shouldNavigateToLogin()

        composeRule.onNodeWithTag(Tags.LoginScreen.emailInput).performTextInput("test@yavshok.ru")
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordInput).performTextInput("QWErty!1")
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
    }
}
