package ru.yavshok.app.tests

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.LoginScreenFixture
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.utility.CredentialsData
import ru.yavshok.app.viewmodel.LoginViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory


@RunWith(AndroidJUnit4::class)
class LoginScreenTests(private val testRule: ComposeTestRule) {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var loginScreen: LoginScreenFixture

    @Before
    fun SetUp() {
        val factory = ViewModelFactory(ApplicationProvider.getApplicationContext())
        val loginViewModel = factory.create(LoginViewModel::class.java)
        composeTestRule.setContent {
            LoginScreen(
                onNavigateToRegister = {},
                onNavigateBack = {},
                onLoginSuccess = {},
                viewModel = loginViewModel
            )
        }
        loginScreen = LoginScreenFixture(composeTestRule)
    }

    @Test
    fun shouldNotEnterWithFakePassword() {
        val message = "Неверный email или пароль"
        loginScreen.loginIntoSystem(CredentialsData.ValidUserData.email, CredentialsData.IncorrectUserData.password).waitErrorMessage(message)
    }

    @Test
    fun shouldNotEnterWithFakeEmail() {
        val message = "Неверный email или пароль"
        loginScreen.loginIntoSystem(CredentialsData.IncorrectUserData.email, CredentialsData.ValidUserData.password).waitErrorMessage(message)
    }

    @Test
    fun shouldNotEnterWithoutPassword() {
        val message = "Заполните все поля"
        loginScreen.loginIntoSystem(CredentialsData.ValidUserData.email, "",).waitErrorMessage(message)
    }

    @Test
    fun shouldAllElementsOnScreen() {
        loginScreen.checkTitle().checkBackButton().checkRegistrationButton().checkPasswordTextField().checkPasswordTextField()
    }
}