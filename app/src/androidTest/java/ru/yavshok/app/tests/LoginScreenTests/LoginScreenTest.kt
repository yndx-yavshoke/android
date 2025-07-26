package ru.yavshok.app.tests.LoginScreenTests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.BuildConfig
import ru.yavshok.app.Tags
import ru.yavshok.app.rules.ScreenRule
import ru.yavshok.helpers.assertTextDisplayed
import ru.yavshok.helpers.login
import ru.yavshok.helpers.setInputText

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class LoginScreenTest: ScreenRule() {

    private val title = Tags.LoginScreen.screenTitle
    private val emailField = Tags.LoginScreen.emailTextField
    private val passwordField = Tags.LoginScreen.passwordTextField
    private val errorMessage = Tags.LoginScreen.errorMessage

    @Before
    fun setup() {
        setLoginScreenContent()
        composeRule.assertTextDisplayed(title, "Войти в ШОК")
    }

    @Test
    fun shouldTypeEmailOnLoginScreen() {
        val email = "test@test.com"
        composeRule.setInputText(emailField, email)
        composeRule.assertTextDisplayed(emailField, email)
    }

    @Test
    fun shouldTypePasswordOnLoginScreen() {
        composeRule.setInputText(passwordField, "123456")
    }

    @Test
    fun authorizationOfUnregisteredUser() {
        composeRule.login("unregister@unreg.com", "123456")
        composeRule.assertTextDisplayed(errorMessage, "Неверный email или пароль")
    }

    @Test
    fun authorizationWithEmptyFields() {
        composeRule.login("", "")
        composeRule.assertTextDisplayed(errorMessage, "Заполните все поля")
    }

    @Test
    fun authorizationWithEmptyPasswordField() {
        composeRule.login("gmail@gmail.com", "")
        composeRule.assertTextDisplayed(errorMessage, "Заполните все поля")
    }

    @Test
    fun authorizationWithEmptyEmailField() {
        composeRule.login("", "123456")
        composeRule.assertTextDisplayed(errorMessage, "Заполните все поля")
    }
}