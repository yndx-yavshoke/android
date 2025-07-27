package ru.yavshok.app.fixtures

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags
import ru.yavshok.app.utility.CredentialsData

class MainScreenFixture(private val testRule: ComposeTestRule){
    private val title = testRule.onNodeWithTag(Tags.MainScreen.screenTitle)
    private val emailTextField = testRule.onNodeWithTag(Tags.MainScreen.emailTextField)
    private val checkButton = testRule.onNodeWithTag(Tags.MainScreen.checkButton)
    private val loginButton = testRule.onNodeWithTag(Tags.MainScreen.loginButton)
    private val successMessage = testRule.onNodeWithTag(Tags.MainScreen.successMessage)
    private val failedMessage = testRule.onNodeWithTag(Tags.MainScreen.failedMessage)

    // Проверка заголовка экрана
    fun checkTitle(): MainScreenFixture {
        title.assertIsDisplayed()
        title.assertTextEquals("Я в ШОКе")
        return this
    }

    // Проверка поля для ввода почты
    fun checkEmailTextField(): MainScreenFixture {
        emailTextField.assertIsDisplayed()
        emailTextField.assertTextContains("Введите Email")
        return this
    }

    // Проверка кнопки "проверки пользователя"
    fun checkCheckButton(): MainScreenFixture {
        checkButton.assertIsDisplayed()
        checkButton.assertTextContains("Я в шоке?")
        return this
    }

    // Проверка кнопки "логина"
    fun checkLoginButton(): MainScreenFixture {
        loginButton.assertIsDisplayed()
        checkButton.assertTextContains("В шок")
        return this
    }

    // Проверка "удачного сообщения" (при условии что он действительно существует)
    fun checkSuccessMessage(): MainScreenFixture {
        emailTextField.assertIsDisplayed()
        emailTextField.performClick()
        emailTextField.performTextInput(CredentialsData.ValidUserData.email)
        checkButton.performClick()
        successMessage.assertIsDisplayed()
        successMessage.assertTextEquals("Ты уже в ШОКе")
        failedMessage.assertIsNotDisplayed()
        return this
    }

    // Проверка "неудачного сообщения" (при условии что он действительно отсутствует)
    fun checkFailedMessage(): MainScreenFixture {
        emailTextField.assertIsDisplayed()
        emailTextField.performClick()
        emailTextField.performTextInput(CredentialsData.ValidUserData.email)
        checkButton.performClick()
        successMessage.assertIsNotDisplayed()
        failedMessage.assertTextEquals("Ты еще не в ШОКе")
        failedMessage.assertIsDisplayed()
        return this
    }

    // Функция на ввод почты в соответствующее поле
    fun enterEmailCredentials(email: String): MainScreenFixture {
        emailTextField.assertIsDisplayed()
        emailTextField.performTextInput(email)
        return this
    }

    // Функция на клик "проверочной кнопки"
    fun clickCheckButton(): MainScreenFixture {
        checkButton.assertIsDisplayed()
        checkButton.performClick()
        return this
    }

    // Функция на клик "кнопки авторизации"
    fun clickLoginButton(): MainScreenFixture {
        loginButton.assertIsDisplayed()
        loginButton.performClick()
        return this
    }



}