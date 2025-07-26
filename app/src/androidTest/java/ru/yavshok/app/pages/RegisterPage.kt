package ru.yavshok.app.pages

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags


@OptIn(ExperimentalTestApi::class)
class RegisterPage(composeRule: ComposeTestRule) : BasePage(composeRule) {
    private val title = Tags.RegistrationScreen.screenTitle
    private val emailTextField = Tags.RegistrationScreen.emailTextField
    private val passwordTextField = Tags.RegistrationScreen.passwordTextField
    private val ageTextField = Tags.RegistrationScreen.ageTextField
    private val registerButton = Tags.RegistrationScreen.registerButton
    private val backButton = Tags.RegistrationScreen.backButton
    private val errorMessage = Tags.RegistrationScreen.errorMessage
    private val emptyFieldErrorText = "Заполните все поля"
    private val invalidEmailErrorText = "Неверный формат email"
    private val invalidPasswordErrorText = "Пароль должен содержать от 5 до 20 символов"
    private val invalidAgeErrorText = "Возраст должен быть от 0 до 99 лет"



    override val nodeIsDisplayed = mutableListOf(
        title,
        emailTextField,
        passwordTextField,
        ageTextField,
        registerButton,
        backButton
    )

    fun fillEmail(email: String) {
        composeRule.onNodeWithTag(emailTextField).performTextInput(email)
    }

    fun fillPassword(password: String) {
        composeRule.onNodeWithTag(passwordTextField).performTextInput(password)
    }

    fun fillAge(age: String) {
        composeRule.onNodeWithTag(ageTextField).performTextInput(age)
    }

    fun clickRegisterButton() {
        composeRule.onNodeWithTag(registerButton).performClick()
    }

    fun clickBackButton() {
        composeRule.onNodeWithTag(backButton).performClick()
    }

    private fun errorMessageIsDisplayed() {
        composeRule.waitUntilExactlyOneExists(
            hasTestTag(errorMessage),
            5_000L
        )
    }

    fun emptyFieldsErrorMessageIsDisplayed() {
        errorMessageIsDisplayed()
        composeRule.onNodeWithText(emptyFieldErrorText).assertIsDisplayed()

    }

    fun invalidEmailErrorMessageIsDisplayed() {
        errorMessageIsDisplayed()
        composeRule.onNodeWithText(invalidEmailErrorText).assertIsDisplayed()

    }

    fun invalidPasswordErrorMessageIsDisplayed() {
        errorMessageIsDisplayed()
        composeRule.onNodeWithText(invalidPasswordErrorText).assertIsDisplayed()

    }

    fun invalidAgeErrorMessageIsDisplayed() {
        errorMessageIsDisplayed()
        composeRule.onNodeWithText(invalidAgeErrorText).assertIsDisplayed()

    }
}

