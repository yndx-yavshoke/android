package ru.yavshok.app.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags
import utils.User

@OptIn(ExperimentalTestApi::class)
class RegisterScreen(private val rule: ComposeTestRule) {
    private val title get() = rule.onNodeWithTag(Tags.RegisterScreen.screenTitle)
    private val emailField get() = rule.onNodeWithTag(Tags.RegisterScreen.emailTextField)
    private val passField get() = rule.onNodeWithTag(Tags.RegisterScreen.passTextField)
    private val ageField get() = rule.onNodeWithTag(Tags.RegisterScreen.ageTextField)
    private val errorText get() = rule.onNodeWithTag(Tags.RegisterScreen.errorText)
    private val registerButton get() = rule.onNodeWithTag(Tags.RegisterScreen.registerButton)
    private val backButton get() = rule.onNodeWithTag(Tags.RegisterScreen.backButton)

    fun waitExists() {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 5000L,
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle)
        )
    }

    fun assertScreenDisplayed() {
        title.assertIsDisplayed()
        emailField.assertIsDisplayed()
        passField.assertIsDisplayed()
        ageField.assertIsDisplayed()
        backButton.assertIsDisplayed()
        registerButton.assertIsDisplayed()
    }

    fun typeEmail(email: String) {
        emailField.performTextInput(email)
    }

    fun typePassword(pass: String) {
        passField.performTextInput(pass)
    }

    fun typeAge(age: String) {
        ageField.performTextInput(age)
    }

    fun clickOnRegister() {
        registerButton.performClick()
    }

    fun clickOnBack() {
        backButton.performClick()
    }

    fun registerNewUser() {
        val user = User.generateRandomUser()

        emailField.performTextInput(user.email)
        passField.performTextInput(user.password)
        ageField.performTextInput(user.age)

        registerButton.performClick()
    }

    fun assertErrorIsEmptyFields() {
        rule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.RegisterScreen.errorText),
            timeoutMillis = 5000L,
        )

        errorText.assertTextEquals("Заполните все поля")
        assertScreenDisplayed()
    }

    fun assertErrorIsIncorrectEmail() {
        rule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.RegisterScreen.errorText),
            timeoutMillis = 5000L,
        )

        errorText.assertTextEquals("Неверный формат email")
        assertScreenDisplayed()
    }

    fun assertErrorIsIncorrectPassword() {
        rule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.RegisterScreen.errorText),
            timeoutMillis = 5000L,
        )

        errorText.assertTextEquals("Пароль должен содержать от 5 до 20 символов")
        assertScreenDisplayed()
    }

    fun assertErrorIsIncorrectAge() {
        rule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.RegisterScreen.errorText),
            timeoutMillis = 5000L,
        )

        errorText.assertTextEquals("Возраст должен быть от 0 до 99 лет")
        assertScreenDisplayed()
    }
}