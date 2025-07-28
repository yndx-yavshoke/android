package ru.yavshok.app.tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.screens.LoginScreen
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.ViewModelFactory
import secrets.MyUser


class LoginScreenTests {
    @get:Rule
    val composeRule = createComposeRule()
    private lateinit var loginScreen: LoginScreen
    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    @Before
    fun setup() {
        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }
        loginScreen = LoginScreen(composeRule)
        loginScreen.assertScreenDisplayed()
    }

    @Test
    fun shouldShowAnErrorWithAnIncorrectPasswordOnLoginScreen() {
        loginScreen.typeEmail(MyUser.EMAIL)
        loginScreen.typePassword("invalid")
        loginScreen.clickOnLogin()

        loginScreen.assertErrorIsInvalidPassword()
    }

    @Test
    fun shouldShowAnErrorWithEmptyFieldsOnLoginScreen() {
        loginScreen.clickOnLogin()

        loginScreen.assertErrorIsEmptyFields()
    }
}