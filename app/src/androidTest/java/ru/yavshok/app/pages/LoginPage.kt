package ru.yavshok.app.pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags
import ru.yavshok.app.ValidUser


@OptIn(ExperimentalTestApi::class)
class LoginPage(composeRule: ComposeTestRule) : BasePage(composeRule) {
    private val title = Tags.LoginScreen.screenTitle
    private val emailTextField = Tags.LoginScreen.emailTextField
    private val passwordTextField = Tags.LoginScreen.passwordTextField
    private val loginButton = Tags.LoginScreen.loginButton
    private val errorMessage = Tags.LoginScreen.errorMessage
    private val emptyFieldErrorText = "Заполните все поля"


    private val registrationButton = Tags.LoginScreen.registrationButton
    private val backButton = Tags.LoginScreen.backButton

    override val nodeIsDisplayed = mutableListOf(
        title,
        emailTextField,
        passwordTextField,
        loginButton,
        registrationButton,
        backButton
    )

    fun fillEmail(email: String) {
        composeRule.onNodeWithTag(emailTextField).performTextInput(email)
    }

    fun fillPassword(password: String) {
        composeRule.onNodeWithTag(passwordTextField).performTextInput(password)
    }

    fun clickLoginButton() {
        composeRule.onNodeWithTag(loginButton).performClick()
    }

    fun clickRegistrationButton() {
        composeRule.onNodeWithTag(registrationButton).performClick()
    }

    fun clickBackButton() {
        composeRule.onNodeWithTag(backButton).performClick()
    }

    private fun errorMessageIsDisplayed() {
        composeRule.waitUntilExactlyOneExists(
            hasTestTag(errorMessage),
            1_000L
        )
    }

    fun loginUser(){
        composeRule.onNodeWithTag(emailTextField).performTextInput(ValidUser.EMAIL)
        composeRule.onNodeWithTag(passwordTextField).performTextInput(ValidUser.PASSWORD)
        composeRule.onNodeWithTag(loginButton).performClick()
    }

    fun emptyFieldsErrorMessageIsDisplayed() {
        errorMessageIsDisplayed()
        composeRule.onNodeWithText(emptyFieldErrorText).assertIsDisplayed()

    }
}

