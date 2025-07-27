package ru.yavshok.app.tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.RegisterScreenFixture
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.utility.CredentialsData
import ru.yavshok.app.viewmodel.RegisterViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class RegisterScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var registerScreen: RegisterScreenFixture

    @Before
    fun setUp() {
        val factory = ViewModelFactory(ApplicationProvider.getApplicationContext())
        val registerViewModel = factory.create(RegisterViewModel::class.java)
        composeTestRule.setContent {
            RegisterScreen(
                onNavigateBack = {},
                onRegistrationSuccess = {},
                viewModel = registerViewModel
            )
        }
        registerScreen = RegisterScreenFixture(composeTestRule)
    }

    @Test
    fun shouldAllElementsOnScreen() {
        registerScreen
            .checkTitle()
            .checkEmailTextField()
            .checkPasswordTextField()
            .checkAgeTextField()
            .checkRegistrationButton()
            .checkBackButton()
    }

    @Test
    fun shouldNotRegisterWithInvalidEmail() {
        val message = "Неверный формат email"
        registerScreen
            .registerProcess("invalid-email", CredentialsData.ValidUserData.password, "25")
            .waitErrorMessage(message)
    }

    @Test
    fun shouldNotRegisterWithEmptyFields() {
        val message = "Заполните все поля"
        registerScreen
            .registerProcess("", "", "")
            .waitErrorMessage(message)
    }

    @Test
    fun shouldNotRegisterWithExistingEmail() {
        val message = "Пользователь с таким email уже существует"
        registerScreen
            .registerProcess(CredentialsData.ValidUserData.email, CredentialsData.ValidUserData.password, "25")
            .waitErrorMessage(message)
    }

    @Test
    fun shouldNotRegisterWithWeakPassword() {
        val message = "Пароль должен содержать минимум 6 символов"
        registerScreen
            .registerProcess("newuser@test.com", "123", "25")
            .waitErrorMessage(message)
    }

    @Test
    fun shouldNotRegisterWithInvalidAge() {
        val message = "Возраст должен быть от 16 до 100 лет"
        registerScreen
            .registerProcess("newuser@test.com", CredentialsData.ValidUserData.password, "10")
            .waitErrorMessage(message)
    }

    @Test
    fun shouldRegisterSuccessfully() {
        registerScreen
            .registerProcess("newuser@test.com", CredentialsData.ValidUserData.password, "25")
    }
} 