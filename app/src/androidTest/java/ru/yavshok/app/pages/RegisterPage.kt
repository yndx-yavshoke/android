package ru.yavshok.app.pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import io.github.serpro69.kfaker.Faker
import ru.yavshok.app.Tags
import ru.yavshok.app.data.Data
import ru.yavshok.app.data.Timeouts

@OptIn(ExperimentalTestApi::class)
class RegisterPage(private val composeRule: ComposeTestRule) {

    val title = composeRule.onNodeWithTag(Tags.RegisterScreen.screenTitle)
    val emailField = composeRule.onNodeWithTag(Tags.RegisterScreen.emailTextField)
    val passwordField = composeRule.onNodeWithTag(Tags.RegisterScreen.passwordTextField)
    val ageField = composeRule.onNodeWithTag(Tags.RegisterScreen.ageTextField)
    val errorMessage = composeRule.onNodeWithTag(Tags.RegisterScreen.errorMessage)
    val registerButton = composeRule.onNodeWithTag(Tags.RegisterScreen.registerButton)
    val backButton = composeRule.onNodeWithTag(Tags.RegisterScreen.backButton)

    val faker = Faker()
    val email = faker.internet.email()

    fun goBackToLoginPage() {
        with(backButton) {
            assertIsDisplayed()
            performClick()
        }
    }

    fun shouldRegisterYoungUser() {
        with(emailField) {
            assertIsDisplayed()
            performClick()
            performTextInput(email)
        }

        with(passwordField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.PASSWORD)
        }

        with(ageField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.YOUNG_AGE)
        }

        with(registerButton) {
            assertIsDisplayed()
            performClick()
        }
    }

    fun shouldRegisterAdultUser() {
        with(emailField) {
            assertIsDisplayed()
            performClick()
            performTextInput(email)
        }

        with(passwordField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.PASSWORD)
        }

        with(ageField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.ADULT_AGE)

        }

        with(registerButton) {
            assertIsDisplayed()
            performClick()
        }
    }

    fun shouldRegisterOldUser() {
        with(emailField) {
            assertIsDisplayed()
            performClick()
            performTextInput(email)
        }

        with(passwordField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.PASSWORD)
        }

        with(ageField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.OLD_AGE)
        }

        with(registerButton) {
            assertIsDisplayed()
            performClick()
        }
    }

    fun shouldNotRegisterAlreadyRegisteredUser() {
        with(emailField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.EXIST_EMAIL)
        }

        with(passwordField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.PASSWORD)
        }

        with(ageField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.YOUNG_AGE)
        }

        with(registerButton) {
            assertIsDisplayed()
            performClick()
        }
        composeRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.RegisterScreen.errorMessage),
            Timeouts.SHORT
        )
        errorMessage.assertIsDisplayed()
    }

    fun shouldNotRegisterShortPassword() {
        with(emailField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.NON_EXIST_EMAIL)
        }

        with(passwordField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.SHORT_PASSWORD)
        }

        with(ageField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.YOUNG_AGE)
        }

        with(registerButton) {
            assertIsDisplayed()
            performClick()
        }
        errorMessage.assertIsDisplayed()
    }

    fun shouldNotRegisterLongPassword() {
        with(emailField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.NON_EXIST_EMAIL)
        }

        with(passwordField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.LONG_PASSWORD)
        }

        with(ageField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.YOUNG_AGE)
        }

        with(registerButton) {
            assertIsDisplayed()
            performClick()
        }
        errorMessage.assertIsDisplayed()
    }

    fun shouldNotRegisterEmptyAgeField() {
        with(emailField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.NON_EXIST_EMAIL)
        }

        with(passwordField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.PASSWORD)
        }

        with(registerButton) {
            assertIsDisplayed()
            performClick()
        }
        errorMessage.assertIsDisplayed()
    }

    fun shouldNotRegisterTooOld() {
        with(emailField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.NON_EXIST_EMAIL)
        }

        with(passwordField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.PASSWORD)
        }

        with(ageField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.VERY_OLD_AGE)
        }

        with(registerButton) {
            assertIsDisplayed()
            performClick()
        }
        errorMessage.assertIsDisplayed()
    }

    fun shouldNotAcceptSQL() {
        with(emailField) {
            assertIsDisplayed()
            performClick()
            performTextInput("SELECT * FROM Users WHERE UserId = 10")
        }

        with(passwordField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.PASSWORD)
        }

        with(ageField) {
            assertIsDisplayed()
            performClick()
            performTextInput(Data.ADULT_AGE)
        }
        with(registerButton) {
            assertIsDisplayed()
            performClick()
        }
        errorMessage.assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitExists() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = Timeouts.SHORT,
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle)
        )
    }
}
