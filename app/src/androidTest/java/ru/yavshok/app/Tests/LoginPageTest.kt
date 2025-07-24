package ru.yavshok.app.Tests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Fixtures.LoginPage
import ru.yavshok.app.Tags
import ru.yavshok.app.utils_data.Users
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.ViewModelFactory




@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalTestApi::class)

class LoginPageTestTest {

    @get:Rule
    val composeRule = createComposeRule()

    val loginScreen = LoginPage(composeRule)
    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())
    private fun launchLoginScreen() {

        val loginViewModel = vmFactory.create(ru.yavshok.app.viewmodel.LoginViewModel::class.java)
        composeRule.setContent {
            LoginScreen(
                viewModel = loginViewModel,
                onNavigateToRegister = {},
                onNavigateBack = {},
                onLoginSuccess = {}
            )
        }
    }

    @Test
    fun enterEmailOnLoginScreen() {
        launchLoginScreen()

        loginScreen.assertTitleIsDisplayed()
            .assertEmailFieldIsDisplayed()
            .enterEmail(Users.registeredUser.email)
            .assertEmailFieldContainsText(Users.registeredUser.email)
    }


    @Test
    fun enterPasswordOnLoginScreen() {
        launchLoginScreen()
        loginScreen.assertTitleIsDisplayed()
            .assertPasswordFieldIsDisplayed()
            .enterPassword(Users.registeredUser.password)
            .assertPasswordFieldContainsText("•••••••")
    }

    @Test
    fun errorMessageWithEmptyEmail() {
        launchLoginScreen()
        loginScreen.assertTitleIsDisplayed()
            .assertEmailFieldIsDisplayed()
            .assertPasswordFieldIsDisplayed()
            .enterPassword(Users.registeredUser.password)
            .clickLoginButton()
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.LoginPage.errorMessage),
            timeoutMillis = 5_000
        )
        loginScreen.assertErrorMessageIsDisplayed()
    }
    fun errorMessageWithEmptyPassword() {
        launchLoginScreen()
        loginScreen.assertTitleIsDisplayed()
            .assertEmailFieldIsDisplayed()
            .assertPasswordFieldIsDisplayed()
            .enterEmail(Users.registeredUser.email)
            .clickLoginButton()
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.LoginPage.errorMessage),
            timeoutMillis = 5_000
        )
        loginScreen.assertErrorMessageIsDisplayed()
    }

}
