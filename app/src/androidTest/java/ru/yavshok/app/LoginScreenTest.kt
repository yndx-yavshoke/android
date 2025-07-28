package ru.yavshok.app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.screens.LoginScreenPage
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.LoginViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val loginScreen by lazy { LoginScreenPage(composeRule) }

    private lateinit var onLoginSuccess: () -> Unit

    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())
    val loginViewModel = vmFactory.create(LoginViewModel::class.java)

    @Before
    fun setup() {
        onLoginSuccess = {}
        composeRule.setContent {
            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = onLoginSuccess
            )
        }
    }

    @Test
    fun shouldDisplayAllUIElementsWithCorrectTextsOnLoginScreen() {
        loginScreen
            .waitExistTitle()
            .checkTitleIsDisplayed()
            .checkTitleTextIsDisplayed()
            .checkEmailPlaceholderIsDisplayed()
            .checkPasswordPlaceholderIsDisplayed()
            .checkLoginButtonIsDisplayed()
            .checkBackButtonIsDisplayed()
            .checkRegisterButtonIsDisplayed()
    }

    @Test
    fun shouldShowErrorForEmptyFieldsOnLoginScreen() {
        loginScreen
            .waitExistTitle()
            .clickLogin()
            .waitErrorMessage()
            .checkErrorText("Заполните все поля")
    }

    @Test
    fun shouldShowErrorForInvalidEmailFormatOnLoginScreen() {
        loginScreen
            .waitExistTitle()
            .login("invalid-email", "123456")
            .waitErrorMessage()
            .checkErrorText("Неверный формат email")
    }

    @Test
    fun shouldShowErrorForNonExistentUserOnLoginScreen() {
        loginScreen
            .waitExistTitle()
            .login("notfound@yavshok.ru", "password123")
            .waitErrorMessage()
            .checkErrorText("Неверный email или пароль")
    }
}