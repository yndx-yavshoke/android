package ru.yavshok.app.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class RegisterScreenPageObject(private val testRule: ComposeTestRule) {
    private val title = testRule.onNodeWithTag(Tags.RegisterScreen.screenTitle)
    private val emailField = testRule.onNodeWithTag(Tags.RegisterScreen.emailTextField)
    private val passwordField = testRule.onNodeWithTag(Tags.RegisterScreen.passwordTextField)
    private val ageField = testRule.onNodeWithTag(Tags.RegisterScreen.ageTextField)
    private val registerButton = testRule.onNodeWithTag(Tags.RegisterScreen.registerButton)
    private val backButton = testRule.onNodeWithTag(Tags.RegisterScreen.backButton)

    fun enterEmail(email: String): RegisterScreenPageObject {
        emailField.assertIsDisplayed()
        emailField.performTextInput(email)
        return this
    }

    fun enterPassword(password: String): RegisterScreenPageObject {
        passwordField.assertIsDisplayed()
        passwordField.performTextInput(password)
        return this
    }

    fun enterAge(age: String): RegisterScreenPageObject {
        ageField.assertIsDisplayed()
        ageField.performTextInput(age)
        return this
    }

    fun clickRegister(): RegisterScreenPageObject {
        registerButton.assertIsDisplayed()
        registerButton.performClick()
        return this
    }

    fun clickBackButton(): RegisterScreenPageObject {
        backButton.assertIsDisplayed()
        backButton.performClick()
        return this
    }

    fun register(email: String, password: String, age: String): RegisterScreenPageObject {
        return enterEmail(email).enterPassword(password).enterAge(age).clickRegister()
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitTitle(): RegisterScreenPageObject {
        testRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.RegisterScreen.screenTitle),
            5000)
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitErrorMessage(message: String): RegisterScreenPageObject {
        testRule.waitUntilAtLeastOneExists(
            hasTextExactly(message),
            5000
        )
        return this
    }

    fun assertErrorMessage(message: String): RegisterScreenPageObject {
        testRule.onNodeWithText(message)
            .assertIsDisplayed()
        return this
    }

    fun assertTitle(): RegisterScreenPageObject {
        title
            .assertIsDisplayed()
            .assertTextContains("Регистрация в ШОКе")
        return this
    }

    fun assertEmailField(): RegisterScreenPageObject {
        emailField
            .assertIsDisplayed()
            .assertTextContains("Введите email")
        return this
    }

    fun assertPasswordField(): RegisterScreenPageObject {
        passwordField
            .assertIsDisplayed()
            .assertTextContains("Пароль")
        return this
    }

    fun assertAgeField(): RegisterScreenPageObject {
        ageField
            .assertIsDisplayed()
            .assertTextContains("Возраст")
        return this
    }

    fun assertRegisterButton(): RegisterScreenPageObject {
        registerButton
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertTextContains("Зарегистрироваться")
        return this
    }

    fun assertBackButton(): RegisterScreenPageObject {
        backButton
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertTextContains("Назад")
        return this
    }
}