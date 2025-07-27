package ru.yavshok.app.tests.RegisterScreenTests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.BuildConfig
import ru.yavshok.app.Tags
import ru.yavshok.app.rules.ScreenRule
import ru.yavshok.helpers.assertTextDisplayed
import ru.yavshok.helpers.register
import ru.yavshok.helpers.setInputText

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class RegisterScreenTest: ScreenRule() {

    private val title = Tags.RegisterScreen.screenTitle
    private val emailField = Tags.RegisterScreen.emailTextField
    private val passwordField = Tags.RegisterScreen.passwordTextField
    private val ageField = Tags.RegisterScreen.ageTextField
    private val errorMessage = Tags.RegisterScreen.errorMessage

    @Before
    fun setup() {
        setRegisterScreenContent()
        composeRule.assertTextDisplayed(title, "Регистрация в ШОКе")
    }

    @Test
    fun shouldTypeEmailOnRegisterScreen() {
        val email = "test@test.com"
        composeRule.setInputText(emailField, email)
        composeRule.assertTextDisplayed(emailField, email)
    }

    @Test
    fun shouldTypePasswordOnRegisterScreen() {
        composeRule.setInputText(passwordField, "123456")
    }

    @Test
    fun shouldTypeAgeOnRegisterScreen() {
        composeRule.setInputText(ageField, "18")
        composeRule.assertTextDisplayed(ageField, "18")
    }

    @Test
    fun registrationOfRegisteredUser() {
        val email = BuildConfig.EMAIL
        val password = BuildConfig.PASSWORD
        composeRule.register(email, password, "18")
        composeRule.assertTextDisplayed(errorMessage, "Пользователь с таким email уже существует")
    }

    @Test
    fun registrationWithInvalidEmail() {
        composeRule.register("examplegmailcom", "12345", "18")
        composeRule.assertTextDisplayed(errorMessage, "Неверный формат email")
    }

    @Test
    fun registrationWithInvalidPassword() {
        composeRule.register("test@test.com", "1234", "18") // в веб версии пароль от 6 символов!
        composeRule.assertTextDisplayed(errorMessage, "Пароль должен содержать от 5 до 20 символов")
    }

    @Test
    fun registrationWithInvalidAge() {
        composeRule.register("test@test.com", "12345", "100")
        composeRule.assertTextDisplayed(errorMessage, "Возраст должен быть от 0 до 99 лет")
    }
}