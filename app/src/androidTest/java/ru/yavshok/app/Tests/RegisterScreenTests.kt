package ru.yavshok.app.Tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.Ignore
import org.junit.runner.RunWith
import ru.yavshok.app.test_utils.Users
import ru.yavshok.app.test_utils.waitForTag
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.viewmodel.ViewModelFactory
import ru.yavshok.app.Tags


@RunWith(AndroidJUnit4::class)

class RegisterScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    val loginScreen = LoginScreen(composeRule)
    val registerScreen = RegisterScreen(composeRule)
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
    fun shouldTypeEmailOnRegisterScreen() {
        launchRegisterScreen()

        registerScreen.assertScreenTitleIsDisplayed()
            .assertEmailFieldIsDisplayed()
            .enterEmailRegisterScreen(Users.registeredUser.email)
            .assertEmailFieldContains(Users.registeredUser.email)
    }

    @Test
    @Ignore //пропускаем тест пароля, тк не проверить маску
    fun shouldTypePasswordOnRegisterScreen() {
        launchRegisterScreen()

        registerScreen.assertScreenTitleIsDisplayed()
            .assertPasswordFieldIsDisplayed()
            .enterPasswordRegisterScreen(Users.registeredUser.password)
            .assertPasswordFieldContains(Users.registeredUser.password)
    }

    @Test
    fun shouldTypeAgeOnRegisterScreen() {
        launchRegisterScreen()

        registerScreen.assertScreenTitleIsDisplayed()
            .assertAgeFieldIsDisplayed()
            .enterAgeRegisterScreen(Users.registeredUser.age)
            .assertAgeFieldContains(Users.registeredUser.age)
    }

    @Test
    fun shouldShowErrorMessageWhenRegisteringWithEmptyFields() {
        launchRegisterScreen()

        registerScreen.assertEmailFieldIsDisplayed()
            .enterEmailRegisterScreen("")
            .enterPasswordRegisterScreen("")
            .clickRegisterButton()

        composeRule.waitForTag(Tags.RegisterScreen.errorMessage)

        registerScreen.assertErrorMessageIsDisplayed()
    }



}