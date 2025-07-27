package ru.yavshok.app.tests

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.yavshok.TestUtils
import ru.yavshok.app.pages.RegisterScreenPage
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.viewmodel.RegisterViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory

class RegisterScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var viewModel: RegisterViewModel
    private lateinit var page: RegisterScreenPage

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        viewModel = ViewModelFactory(context).create(RegisterViewModel::class.java)
        viewModel.resetRegistrationState()

        composeRule.setContent {
            RegisterScreen(
                onNavigateBack = {},
                onRegistrationSuccess = {},
                viewModel = viewModel
            )
        }

        page = RegisterScreenPage(composeRule)
    }

    @Test
    fun shouldDisplayRegisterScreenElements() {
        page.assertAllElementsDisplayed()
    }

    @Test
    fun shouldRegisterSuccessfullyWithValidData() {
        val email = TestUtils.generateUniqueEmail()
        val password = "qwerty"
        val age = "25"

        page.typeEmail(email)
        page.typePassword(password)
        page.typeAge(age)
        page.clickSubmit()

        composeRule.waitUntil(timeoutMillis = 5_000) {
            viewModel.uiState.value.isRegistrationSuccessful
        }
    }

    @Test
    fun shouldShowErrorForTooLongPassword() {
        val validEmail = TestUtils.generateUniqueEmail()
        val longPassword = "p".repeat(25)
        val age = "30"

        page.typeEmail(validEmail)
        page.typePassword(longPassword)
        page.typeAge(age)
        page.clickSubmit()

        page.waitForErrorAndAssert("Пароль должен содержать от 5 до 20 символов")
    }

    @Test
    fun shouldShowErrorForTooLongEmail() {
        val longEmail = "a".repeat(51) + "@mail.com" // > 50 символов
        val validPassword = "securePass"
        val age = "30"

        page.typeEmail(longEmail)
        page.typePassword(validPassword)
        page.typeAge(age)
        page.clickSubmit()

        page.waitForErrorAndAssert("Email не должен превышать 50 символов")
    }

}
