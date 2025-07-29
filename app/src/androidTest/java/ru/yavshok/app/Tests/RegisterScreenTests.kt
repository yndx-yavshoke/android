package ru.yavshok.app.Tests.RegisterScreenTests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.Screens.RegisterScreenPage
import ru.yavshok.app.Utils.DataGenerator
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.viewmodel.ViewModelFactory

@OptIn(ExperimentalTestApi::class)
class RegisterScreenTests {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var registerScreen: RegisterScreenPage
    private lateinit var viewModelFactory: ViewModelFactory

    @Before
    fun setUp() {
        viewModelFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())
        composeRule.setContent {
            RegisterScreen(viewModel = viewModel(factory = viewModelFactory))
        }
        registerScreen = RegisterScreenPage(composeRule)
    }

    @Test
    fun shouldDisplayAllFields() {
        with(registerScreen) {
            assertTitleIsDisplayed()
            assertEmailFieldIsDisplayed()
            assertPasswordFieldIsDisplayed()
            assertAgeFieldIsDisplayed()
            assertRegisterButtonIsDisplayed()
        }
    }

    @Test
    fun shouldDisplayAllPlaceholders() {
        with(registerScreen) {
            assertEmailPlaceholder()
            assertPasswordPlaceholder()
            assertAgePlaceholder()
        }
    }

    @Test
    fun shouldShowErrorOnEmptyFields() {
        with(registerScreen) {
            clickRegisterButton()
            assertAllFieldErrorsAreDisplayed()
        }
    }

    @Test
    fun shouldShowErrorOnInvalidEmail() {
        with(registerScreen) {
            enterEmail("invalid_email")
            enterPassword(DataGenerator.FakeUser.password())
            enterAge(DataGenerator.FakeUser.age())
            clickRegisterButton()
            assertEmailErrorIsDisplayed()
        }
    }

    @Test
    fun shouldShowErrorOnShortPassword() {
        with(registerScreen) {
            enterEmail(DataGenerator.FakeUser.email())
            enterPassword("123")
            enterAge(DataGenerator.FakeUser.age())
            clickRegisterButton()
            assertPasswordErrorIsDisplayed()
        }
    }

    @Test
    fun shouldShowErrorOnTooLongEmail() {
        val longEmail = "a".repeat(42) + "@mail.com"
        with(registerScreen) {
            enterEmail(longEmail)
            enterPassword(DataGenerator.FakeUser.password())
            enterAge(DataGenerator.FakeUser.age())
            clickRegisterButton()
            assertToLongEmailErrorIsDisplayed()
        }
    }

    @Test
    fun shouldShowErrorOnInvalidAge() {
        with(registerScreen) {
            enterEmail(DataGenerator.FakeUser.email())
            enterPassword(DataGenerator.FakeUser.password())
            enterAge("123")
            clickRegisterButton()
            assertAgeErrorIsDisplayed()
        }
    }
}
