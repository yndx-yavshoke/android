package ru.yavshok.app.Tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Fixtures.RegisterPage
import ru.yavshok.app.utils_data.Users
import ru.yavshok.app.utils_data.waitForTag
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.viewmodel.ViewModelFactory
import ru.yavshok.app.Tags


@RunWith(AndroidJUnit4::class)

class RegisterPageTest {
    @get:Rule
    val composeRule = createComposeRule()

    val registerPage = RegisterPage(composeRule)
    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    private fun launchRegisterScreen() {

        val registerViewModel = vmFactory.create(ru.yavshok.app.viewmodel.RegisterViewModel::class.java)

        composeRule.setContent {
            RegisterScreen(
                viewModel = registerViewModel,
                onNavigateBack = {},
                onRegistrationSuccess = {}
            )
        }
    }

    @Test
    fun enterEmailOnRegisterScreen() {
        launchRegisterScreen()

        composeRule.waitForIdle()

        registerPage.assertEmailFieldIsDisplayed()
            .enterEmailRegisterScreen(Users.registeredUser.email)
            .assertEmailFieldContains(Users.registeredUser.email)
    }

    @Test
    fun enterAgeDirectInput() {
        launchRegisterScreen()

        composeRule.waitForIdle()

        registerPage.assertAgeFieldIsDisplayed()
            .enterAgeRegisterScreen(Users.registeredUser.age)
            .assertAgeFieldContains(Users.registeredUser.age)

    }

    @Test
    fun showErrorMessageWhenRegisteringWithEmptyFields() {
        launchRegisterScreen()

        registerPage.assertEmailFieldIsDisplayed()
            .enterEmailRegisterScreen("")
            .enterPasswordRegisterScreen("")
            .clickRegisterButton()

        composeRule.waitForTag(Tags.RegisterPage.errorMessage)

        registerPage.assertErrorMessageIsDisplayed()
    }



}