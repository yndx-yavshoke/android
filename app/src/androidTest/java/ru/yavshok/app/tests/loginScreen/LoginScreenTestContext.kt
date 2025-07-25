package ru.yavshok.app.tests.loginScreen

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.LoginScreenPageObj
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.utils.EnvData
import ru.yavshok.app.utils.FakerDataGenerator
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class LoginScreenTestContext {

    @get:Rule
    val composeRule = createComposeRule()

    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    @Before
    fun setupPage() {
        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }
    }


    @Test
    fun shouldThrowWarningForIncorrectUserEmail() {
        LoginScreenPageObj(composeRule)
            .waitForScreen()
            .inputEmail(FakerDataGenerator.randomEmail())
            .inputPassword(EnvData.password)
            .clickOnLoginButton()
            .assertTextIsVisible("Неверный email или пароль")
    }

    @Test
    fun shouldThrowWarningForIncorrectUserPassword() {
        LoginScreenPageObj(composeRule)
            .waitForScreen()
            .inputEmail(EnvData.email)
            .inputPassword(FakerDataGenerator.randomPassword())
            .clickOnLoginButton()
            .assertTextIsVisible("Неверный email или пароль")
    }

    @Test
    fun shouldThrowWarningForInvalidEmailFormat() {
        LoginScreenPageObj(composeRule)
            .waitForScreen()
            .inputEmail(EnvData.email.substringBefore('@'))
            .inputPassword(EnvData.password)
            .clickOnLoginButton()
            .assertTextIsVisible("Неверный формат email")
    }

    @Test
    fun shouldThrowWarningForEmptyEmail() {
        LoginScreenPageObj(composeRule)
            .waitForScreen()
            .assertEmailFieldIsEmpty()
            .inputPassword(EnvData.password)
            .clickOnLoginButton()
            .assertTextIsVisible("Заполните все поля")
    }

    @Test
    fun shouldThrowWarningForEmptyPassword() {
        LoginScreenPageObj(composeRule)
            .waitForScreen()
            .inputEmail(EnvData.email)
            .assertPasswordFieldIsEmpty()
            .clickOnLoginButton()
            .assertTextIsVisible("Заполните все поля")
    }

    @Test
    fun shouldThrowWarningForEmptyFields() {
        LoginScreenPageObj(composeRule)
            .waitForScreen()
            .assertEmailFieldIsEmpty()
            .assertPasswordFieldIsEmpty()
            .clickOnLoginButton()
            .assertTextIsVisible("Заполните все поля")
    }

}
