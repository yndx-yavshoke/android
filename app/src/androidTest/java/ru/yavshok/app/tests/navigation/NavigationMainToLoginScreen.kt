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

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class NavigationMainToLoginScreen {

    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    val mainPage = MainPage(composeRule)
    val loginPage = LoginPage(composeRule)

    @Test
    fun shouldNavigateFromMainScreenToLoginScreen() {
        mainPage.waitExists()
        mainPage.goToLogin()

        loginPage.waitExists()

        mainPage.title.assertDoesNotExist()
        loginPage.title.assertIsDisplayed()
    }
}