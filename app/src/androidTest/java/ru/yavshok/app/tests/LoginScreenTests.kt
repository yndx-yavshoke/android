package ru.yavshok.app.tests

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import ru.yavshok.TestDataId
import ru.yavshok.app.pages.LoginScreenPage
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.LoginViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class LoginScreenTests {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var viewModel: LoginViewModel
    private lateinit var loginPage: LoginScreenPage

    private var loginSuccessCalled = false

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        viewModel = ViewModelFactory(context).create(LoginViewModel::class.java)

        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel,
                onNavigateBack = {},
                onNavigateToRegister = {},
                onLoginSuccess = { loginSuccessCalled = true }
            )
        }

        loginSuccessCalled = false
        loginPage = LoginScreenPage(composeRule)
    }

    @Test
    fun shouldDisplayLoginScreenElements() {
        loginPage.assertAllElementsVisible()
    }

    @Test
    fun shouldLoginSuccessfully() {
        loginPage.typeEmail(TestDataId.registeredUser.email)
        loginPage.typePassword(TestDataId.registeredUser.password)
        loginPage.clickSubmit()

        composeRule.waitUntil(timeoutMillis = 5_000) {
            loginSuccessCalled
        }

        assert(loginSuccessCalled)
    }

    @Test
    fun shouldShowErrorIfFieldsAreEmpty() {
        loginPage.clickSubmit()
        loginPage.waitForErrorAndAssert("Заполните все поля")
    }

    @Test
    fun shouldShowErrorForWrongPassword() {
        loginPage.typeEmail(TestDataId.registeredUser.email)
        loginPage.typePassword("wrongPassword123")
        loginPage.clickSubmit()
        loginPage.waitForErrorAndAssert("Неверный email или пароль")
    }
}
