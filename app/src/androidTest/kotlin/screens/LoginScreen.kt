package screens

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import faker.FakeDataFactory
import ru.yavshok.app.Tags
import kotlin.math.log

class LoginScreen(private val rule:ComposeTestRule) {

    private val faker = FakeDataFactory

    val title = rule.onNodeWithTag(Tags.LoginScreen.screenTitle)
    val inputEmail = rule.onNodeWithTag(Tags.LoginScreen.inputEmail)
    val inputPassword = rule.onNodeWithTag(Tags.LoginScreen.inputPassword)
    val loginButton = rule.onNodeWithTag(Tags.LoginScreen.loginButton)
    val backButton = rule.onNodeWithTag(Tags.LoginScreen.backButton)
    val registerButton = rule.onNodeWithTag(Tags.LoginScreen.registerButton)
    val errorMessage = rule.onNodeWithTag(Tags.LoginScreen.errorMessage)


    fun checkDefaultState() {
        title.assertIsDisplayed()
        inputEmail.assertIsDisplayed()
        inputPassword.assertIsDisplayed()
        loginButton.assertIsEnabled()
        backButton.assertHasClickAction()
        registerButton.assertHasClickAction()
    }

    fun navigateBackToMainScreen() {
        backButton.performClick()
    }

    fun navigateToRegisterPage() {
        registerButton.performClick()
    }

    fun showErrorMessageWhenFieldsAreEmpty() {
        inputEmail.performTextInput("")
        inputPassword.performTextInput("")
        loginButton.performClick()

        errorMessage.assertIsDisplayed()
    }

    fun showErrorMessageWhenEmailIsNotValid() {
        inputEmail.performTextInput(faker.createInvalidEmail())
        inputPassword.performTextInput(faker.createValidPassword())

        loginButton.performClick()
        errorMessage.assertIsDisplayed()
    }

    fun showErrorMessageWhenPasswordIsWrong() {
        inputEmail.performTextInput(faker.createValidEmail())
        inputPassword.performTextInput(faker.createInvalidPassword())

        loginButton.performClick()
        errorMessage.assertIsDisplayed()
    }

    fun loginSuccessfully() {
        inputEmail.performTextInput(faker.createValidEmail())
        inputPassword.performTextInput(faker.createValidPassword())

        loginButton.performClick()
    }
}