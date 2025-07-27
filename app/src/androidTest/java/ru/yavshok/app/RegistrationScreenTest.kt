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
    val randomString = faker.lorem().characters(10)
    val fakePassword = faker.internet().password()
}

@RunWith(AndroidJUnit4::class)
class RegistrationTest : BaseNavigationTest() {

    fun shouldNavigateToRegistration() = with(composeRule) {
        waitForMainScreen()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        clickLoginButton()
        waitForLoginScreen()
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertDoesNotExist()
        composeRule.onNodeWithTag(Tags.LoginScreen.registrationButton).performClick()
    }

    @Test
    fun shouldCheckRegistrationScreenElements() {
        shouldNavigateToRegistration()

        composeRule.onNodeWithTag(Tags.RegistrationScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegistrationScreen.emailInput).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegistrationScreen.passwordInput).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegistrationScreen.ageInput).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegistrationScreen.registrationButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegistrationScreen.backButton).assertIsDisplayed()
    }

    @Test
    fun shouldCheckWrongEmail() {
        shouldNavigateToRegistration()

        composeRule.onNodeWithTag(Tags.RegistrationScreen.emailInput).performTextInput(randomString)
        composeRule.onNodeWithTag(Tags.RegistrationScreen.passwordInput).performTextInput(fakePassword)
        composeRule.onNodeWithTag(Tags.RegistrationScreen.ageInput).performTextInput("25")
        composeRule.onNodeWithTag(Tags.RegistrationScreen.registrationButton).performClick()
        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule.onAllNodesWithTag(Tags.RegistrationScreen.errorState).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag(Tags.LoginScreen.errorState).assertIsDisplayed()
    }

    @Test
    fun shouldCheckWrongPassword() {
        shouldNavigateToRegistration()

        composeRule.onNodeWithTag(Tags.RegistrationScreen.emailInput).performTextInput(fakeEmail)
        composeRule.onNodeWithTag(Tags.RegistrationScreen.passwordInput).performTextInput(fakePassword)
        composeRule.onNodeWithTag(Tags.RegistrationScreen.ageInput).performTextInput("25")
        composeRule.onNodeWithTag(Tags.RegistrationScreen.registrationButton).performClick()
        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule.onAllNodesWithTag(Tags.RegistrationScreen.errorState).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag(Tags.LoginScreen.errorState).assertIsDisplayed()
    }

    @Test
    fun shouldCheckWrongAge() {
        shouldNavigateToRegistration()

        composeRule.onNodeWithTag(Tags.RegistrationScreen.emailInput).performTextInput(fakeEmail)
        composeRule.onNodeWithTag(Tags.RegistrationScreen.passwordInput).performTextInput(fakePassword)
        composeRule.onNodeWithTag(Tags.RegistrationScreen.ageInput).performTextInput(randomString)
        composeRule.onNodeWithTag(Tags.RegistrationScreen.registrationButton).performClick()
        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule.onAllNodesWithTag(Tags.RegistrationScreen.errorState).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag(Tags.LoginScreen.errorState).assertIsDisplayed()
    }

    @Test
    fun shouldCheckEmptyFields() {
        shouldNavigateToRegistration()

        composeRule.onNodeWithTag(Tags.RegistrationScreen.emailInput).performTextInput("")
        composeRule.onNodeWithTag(Tags.RegistrationScreen.passwordInput).performTextInput("")
        composeRule.onNodeWithTag(Tags.RegistrationScreen.ageInput).performTextInput("")
        composeRule.onNodeWithTag(Tags.RegistrationScreen.registrationButton).performClick()
        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule.onAllNodesWithTag(Tags.RegistrationScreen.errorState).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag(Tags.LoginScreen.errorState).assertIsDisplayed()
    }

}