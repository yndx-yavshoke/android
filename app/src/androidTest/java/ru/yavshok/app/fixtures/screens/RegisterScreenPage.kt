package ru.yavshok.app.fixtures.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class RegisterScreenPage (private val composeRule: ComposeTestRule) {
    val title = composeRule.onNodeWithTag(Tags.RegisterScreen.screenTitle)
    val emailTextField = composeRule.onNodeWithTag(Tags.RegisterScreen.emailTextField)
    val passwordTextField = composeRule.onNodeWithTag(Tags.RegisterScreen.passwordTextField)
    val ageTextField = composeRule.onNodeWithTag(Tags.RegisterScreen.ageTextField)
    val backButton = composeRule.onNodeWithTag(Tags.RegisterScreen.backButton)
    val registerButton = composeRule.onNodeWithTag(Tags.RegisterScreen.registerButton)
    val errorMessage = composeRule.onNodeWithTag(Tags.RegisterScreen.errorMessage)

    fun checkTitleIsDisplayed(): RegisterScreenPage {
        title.assertIsDisplayed()
        return this
    }

    fun checkTitleTextIsDisplayed(expected: String = "Регистрация в ШОКе"): RegisterScreenPage {
        title.assertTextContains(expected)
        return this
    }

    fun checkEmailPlaceholderIsDisplayed(): RegisterScreenPage {
        emailTextField.assertIsDisplayed()
        emailTextField.assertTextContains("Введите email")
        return this
    }

    fun checkPasswordPlaceholderIsDisplayed(): RegisterScreenPage {
        passwordTextField.assertIsDisplayed()
        passwordTextField.assertTextContains("Пароль")
        return this
    }

    fun checkAgePlaceholderIsDisplayed(): RegisterScreenPage {
        ageTextField.assertIsDisplayed()
        ageTextField.assertTextContains("Возраст")
        return this
    }

    fun checkRegisterButtonIsDisplayed(): RegisterScreenPage {
        registerButton.assertIsDisplayed()
        registerButton.assertTextContains("Зарегистрироваться")
        return this
    }

    fun checkBackButtonIsDisplayed(): RegisterScreenPage {
        backButton.assertIsDisplayed()
        backButton.assertTextContains("Назад")
        return this
    }

    fun checkErrorText(expected: String): RegisterScreenPage {
        errorMessage.assertIsDisplayed()
        errorMessage.assertTextContains(expected)
        return this
    }

    fun typeEmail(email: String) : RegisterScreenPage {
        emailTextField.performTextInput(email)
        return this
    }

    fun typePassword(password: String) : RegisterScreenPage {
        passwordTextField.performTextInput(password)
        return this
    }

    fun typeAge(age: String) : RegisterScreenPage {
        ageTextField.performTextInput(age)
        return this
    }

    fun clickRegister() : RegisterScreenPage {
        registerButton.performClick()
        return this
    }

    fun clickBack() : RegisterScreenPage {
        backButton.performClick()
        return this
    }

    fun register(email: String, password: String, age: String) : RegisterScreenPage {
        return typeEmail(email).typePassword(password).typeAge(age).clickRegister()
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitExistTitle(): RegisterScreenPage {
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle),
            timeoutMillis = 5_000L
        )
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitErrorMessage(): RegisterScreenPage {
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.RegisterScreen.errorMessage),
            timeoutMillis = 5_000L
        )
        return this
    }
}