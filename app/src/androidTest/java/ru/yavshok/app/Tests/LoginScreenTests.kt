package ru.yavshok.app.Tests.LoginScreenTests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Screens.LoginScreenPage
import ru.yavshok.app.Utils.DataGenerator
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class LoginScreenTests {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var loginScreen: LoginScreenPage
    private lateinit var viewModelFactory: ViewModelFactory

    @Before
    fun setUp() {
        viewModelFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())
        composeRule.setContent {
            LoginScreen(viewModel = viewModel(factory = viewModelFactory))
        }
        loginScreen = LoginScreenPage(composeRule)
    }

    @Test
    fun shouldDisplayAllLoginFields() {
        with(loginScreen) {
            assertTitleIsDisplayed()
            assertEmailFieldIsDisplayed()
            assertPasswordFieldIsDisplayed()
            assertLoginButtonIsDisplayed()
            assertRegisterButtonIsDisplayed()
        }
    }

    @Test
    fun shouldDisplayLoginPlaceholders() {
        with(loginScreen) {
            assertEmailPlaceholder()
            assertPasswordPlaceholder()
        }
    }

    @Test
    fun shouldShowErrorOnEmptyFields() {
        with(loginScreen) {
            clickLoginButton()
            assertAllFieldErrorsAreDisplayed()
        }
    }

    @Test
    fun shouldShowErrorOnInvalidEmailFormat() {
        with(loginScreen) {
            enterEmail("invalid-email")
            enterPassword(DataGenerator.FakeUser.password())
            clickLoginButton()
            assertEmailErrorIsDisplayed()
        }
    }

    @Test
    fun shouldShowErrorOnWrongCredentials() {
        with(loginScreen) {
            enterEmail("wrong@mail.com")
            enterPassword(DataGenerator.FakeUser.password())
            clickLoginButton()
            assertLoginFailedErrorIsDisplayed()
        }
    }

    @Test
    fun shouldNavigateToRegisterScreen() {
        with(loginScreen) {
            clickRegisterButton()
        }
    }

    @Test
    fun shouldLoginSuccessfullyWithValidCredentials() {
        with(loginScreen) {
            enterEmail(DataGenerator.TestUser.existingUserEmail)
            enterPassword(DataGenerator.TestUser.existingUserPassword)
            clickLoginButton()
        }
    }
}
