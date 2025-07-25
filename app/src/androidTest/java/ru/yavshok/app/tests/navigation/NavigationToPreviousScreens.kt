package ru.yavshok.app.tests.navigation

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.pages.LoginPage
import ru.yavshok.app.pages.MainPage
import ru.yavshok.app.pages.RegisterPage


@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class NavigationToPreviousScreens {

    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    val mainPage = MainPage(composeRule)
    val loginPage = LoginPage(composeRule)
    val registerPage = RegisterPage(composeRule)

    @Test
    fun shouldNavigateBackFromLoginToMainScreen() {
        mainPage.waitExists()
        mainPage.goToLogin()

        loginPage.waitExists()
        loginPage.goBackToMainScreen()

        loginPage.title.assertDoesNotExist()
        mainPage.title.assertIsDisplayed()
    }

    @Test
    fun shouldNavigateBackFromRegisterToLoginScreen() {
        mainPage.waitExists()
        mainPage.goToLogin()

        loginPage.waitExists()
        loginPage.goToRegister()

        registerPage.waitExists()

        registerPage.goBackToLoginPage()

        registerPage.title.assertDoesNotExist()
        loginPage.title.assertIsDisplayed()
    }
}
