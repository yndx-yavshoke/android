package ru.yavshok.app.Screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import ru.yavshok.app.Tags
import androidx.compose.ui.test.ExperimentalTestApi

@OptIn(ExperimentalTestApi::class)
class LoginScreenPage(private val rule: ComposeTestRule) {

    private val titleTag = Tags.LoginScreen.screenTitle
    private val emailTag = Tags.LoginScreen.screenEmailTextField
    private val passwordTag = Tags.LoginScreen.screenPasswordTextField
    private val loginButtonTag = Tags.LoginScreen.screenEnterButton
    private val registerButtonTag = Tags.LoginScreen.screenRegisterButton
    private val errorTag = Tags.LoginScreen.errorMessage
    private val backButton = Tags.LoginScreen.screenBackButton

    private val emailPlaceholder = "Email"
    private val passwordPlaceholder = "Пароль"
    private val emptyFieldsError = "Заполните все поля"
    private val invalidEmailError = "Неверный формат email"
    private val loginFailedError = "Неверный email или пароль"

    fun assertTitleIsDisplayed() = apply {
        rule.onNodeWithTag(titleTag).assertIsDisplayed()
    }

    fun assertEmailFieldIsDisplayed() = apply {
        rule.onNodeWithTag(emailTag).assertIsDisplayed()
    }

    fun assertPasswordFieldIsDisplayed() = apply {
        rule.onNodeWithTag(passwordTag).assertIsDisplayed()
    }

    fun assertLoginButtonIsDisplayed() = apply {
        rule.onNodeWithTag(loginButtonTag).assertIsDisplayed()
    }

    fun assertRegisterButtonIsDisplayed() = apply {
        rule.onNodeWithTag(registerButtonTag).assertIsDisplayed()
    }

    fun enterEmail(email: String) = apply {
        rule.onNodeWithTag(emailTag).performTextInput(email)
    }

    fun enterPassword(password: String) = apply {
        rule.onNodeWithTag(passwordTag).performTextInput(password)
    }

    fun clickLoginButton() = apply {
        rule.onNodeWithTag(loginButtonTag).performClick()
    }

    fun clickRegisterButton() = apply {
        rule.onNodeWithTag(registerButtonTag).performClick()
    }

    fun clickButtonBack() = apply {
        rule.onNodeWithTag(backButton).performClick()
    }

    fun assertEmailPlaceholder() = apply {
        rule.onNodeWithTag(emailTag).assertTextContains(emailPlaceholder)
    }

    fun assertPasswordPlaceholder() = apply {
        rule.onNodeWithTag(passwordTag).assertTextContains(passwordPlaceholder)
    }

    fun assertAllFieldErrorsAreDisplayed() = apply {
        rule.onNodeWithTag(errorTag).assertIsDisplayed()
        rule.onNodeWithTag(errorTag).assertTextContains(emptyFieldsError)
    }

    fun assertEmailErrorIsDisplayed() = apply {
        rule.onNodeWithTag(errorTag).assertIsDisplayed()
        rule.onNodeWithTag(errorTag).assertTextContains(invalidEmailError)
    }

    fun waitExists() {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(titleTag)
        )
    }

    fun assertLoginFailedErrorIsDisplayed() = apply {
        rule.waitUntil(timeoutMillis = 3000) {
            rule.onAllNodesWithTag(errorTag).fetchSemanticsNodes().isNotEmpty()
        }
        rule.onNodeWithTag(errorTag).assertIsDisplayed()
        rule.onNodeWithTag(errorTag).assertTextContains(loginFailedError)
    }
}
