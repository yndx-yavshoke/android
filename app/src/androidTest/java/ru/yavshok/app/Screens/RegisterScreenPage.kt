package ru.yavshok.app.Screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import ru.yavshok.app.Tags
import androidx.compose.ui.test.ExperimentalTestApi

@OptIn(ExperimentalTestApi::class)
class RegisterScreenPage(private val rule: ComposeTestRule) {

    private val titleTag = Tags.RegisterScreen.screenTitle
    private val emailFieldTag = Tags.RegisterScreen.emailTextField
    private val passwordFieldTag = Tags.RegisterScreen.passwordTextField
    private val ageFieldTag = Tags.RegisterScreen.ageTextField
    private val registerButtonTag = Tags.RegisterScreen.registerButton
    private val backButtonTag = Tags.RegisterScreen.backButton
    private val errorMessageTag = Tags.RegisterScreen.errorMessage

    private val placeholderEmail = "Введите email"
    private val placeholderPassword = "Пароль"
    private val placeholderAge = "Возраст"

    private val emptyError = "Заполните все поля"
    private val repeatEmailError = "Пользователь с таким email уже существует"
    private val passwordError = "Пароль должен содержать от 5 до 20 символов"
    private val ageError = "Возраст должен быть от 0 до 99 лет"
    private val emailError = "Неверный формат email"
    private val toLongEmailError = "Email не должен превышать 50 символов"

    fun assertTitleIsDisplayed() = apply {
        rule.onNodeWithTag(titleTag).assertIsDisplayed()
    }

    fun assertEmailFieldIsDisplayed() = apply {
        rule.onNodeWithTag(emailFieldTag).assertIsDisplayed()
    }

    fun assertPasswordFieldIsDisplayed() = apply {
        rule.onNodeWithTag(passwordFieldTag).assertIsDisplayed()
    }

    fun assertAgeFieldIsDisplayed() = apply {
        rule.onNodeWithTag(ageFieldTag).assertIsDisplayed()
    }

    fun assertRegisterButtonIsDisplayed() = apply {
        rule.onNodeWithTag(registerButtonTag).assertIsDisplayed()
    }

    fun assertEmailPlaceholder() = apply {
        rule.onNodeWithTag(emailFieldTag).assertTextContains(placeholderEmail)
    }

    fun assertPasswordPlaceholder() = apply {
        rule.onNodeWithTag(passwordFieldTag).assertTextContains(placeholderPassword)
    }

    fun assertAgePlaceholder() = apply {
        rule.onNodeWithTag(ageFieldTag).assertTextContains(placeholderAge)
    }

    fun enterEmail(email: String) = apply {
        rule.onNodeWithTag(emailFieldTag).performTextInput(email)
    }

    fun enterPassword(password: String) = apply {
        rule.onNodeWithTag(passwordFieldTag).performTextInput(password)
    }

    fun enterAge(age: String) = apply {
        rule.onNodeWithTag(ageFieldTag).performTextInput(age)
    }

    fun clickRegisterButton() = apply {
        rule.onNodeWithTag(registerButtonTag).performClick()
    }

    fun clickBackButton() = apply {
        rule.onNodeWithTag(backButtonTag).performClick()
    }

    fun assertAllFieldErrorsAreDisplayed() = apply {
        rule.onNodeWithTag(errorMessageTag).assertTextContains(emptyError)
    }

    fun assertEmailErrorIsDisplayed() = apply {
        rule.onNodeWithTag(errorMessageTag).assertTextContains(emailError)
    }
    fun assertToLongEmailErrorIsDisplayed() = apply {
        rule.onNodeWithTag(errorMessageTag).assertTextContains(toLongEmailError)
    }

    fun assertPasswordErrorIsDisplayed() = apply {
        rule.onNodeWithTag(errorMessageTag).assertTextContains(passwordError)
    }

    fun assertAgeErrorIsDisplayed() = apply {
        rule.onNodeWithTag(errorMessageTag).assertTextContains(ageError)
    }

    fun assertRepeatEmailErrorIsDisplayed() = apply {
        rule.onNodeWithTag(errorMessageTag).assertTextContains(repeatEmailError)
    }

    fun timeoutForTitle() = apply {
        rule.waitUntilExactlyOneExists(
            timeoutMillis = 3_000L,
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle)
        )
    }

    fun waitExists() {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(titleTag)
        )
    }

    fun assertTitleIsNotDisplayed() = apply {
        rule.onNodeWithTag(Tags.RegisterScreen.screenTitle).assertDoesNotExist()
    }


}