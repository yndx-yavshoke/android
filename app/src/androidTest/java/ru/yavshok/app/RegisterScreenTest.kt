package ru.yavshok.app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.screens.MainScreenPage
import ru.yavshok.app.fixtures.screens.LoginScreenPage
import ru.yavshok.app.fixtures.screens.ProfileScreenPage
import ru.yavshok.app.fixtures.screens.RegisterScreenPage
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.viewmodel.LoginViewModel
import ru.yavshok.app.viewmodel.RegisterViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class RegisterScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    private val registerScreen by lazy { RegisterScreenPage(composeRule) }
    private val profileScreen by lazy { ProfileScreenPage(composeRule) }

    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())
    val registerViewModel = vmFactory.create(RegisterViewModel::class.java)

    @Before
    fun setup() {
        composeRule.setContent {
            RegisterScreen(
                viewModel = registerViewModel
            )
        }
    }

    @Test
    fun shouldDisplayAllUIElementsOnRegisterScreen() {
        registerScreen
            .checkTitleIsDisplayed()
            .checkTitleTextIsDisplayed()
            .checkEmailPlaceholderIsDisplayed()
            .checkPasswordPlaceholderIsDisplayed()
            .checkAgePlaceholderIsDisplayed()
            .checkRegisterButtonIsDisplayed()
            .checkBackButtonIsDisplayed()
    }

    @Test
    fun shouldShowErrorIfEmailInvalidOnRegisterScreen() {
        registerScreen
            .register(email = "invalid-email", password = "qwerty123", age = "2")
            .waitErrorMessage()
            .checkErrorText("Неверный формат email")
    }

    @Test
    fun shouldShowErrorIfFieldsAreEmptyOnRegisterScreen() {
        registerScreen
            .clickRegister()
            .waitErrorMessage()
            .checkErrorText("Заполните все поля")
    }

    @Test
    fun shouldShowErrorIfUserAlreadyExistsOnRegisterScreen() {
        val existingEmail = "valerii.mrm@yandex.ru"

        registerScreen
            .register(email = existingEmail, password = "qwerty123", age = "2")
            .waitErrorMessage()
            .checkErrorText("Пользователь с таким email уже существует")
    }

    @Test
    fun shouldShowErrorIfAgeNotNumberOnRegisterScreen() {
        registerScreen
            .register(email = "newuser@example.com", password = "qwerty123", age = "много")
            .waitErrorMessage()
            .checkErrorText("Возраст должен быть от 0 до 99 лет")
    }

    @Test
    fun shouldShowErrorIfPasswordTooShortOnRegisterScreen() {
        registerScreen
            .register(email = "newuser@example.com", password = "123", age = "3")
            .waitErrorMessage()
            .checkErrorText("Пароль должен содержать от 5 до 20 символов")
    }

//    @Test
//    fun shouldRegisterSuccessfullyWithValidData() {
//        val uniqueEmail = "test_${System.currentTimeMillis()}@example.com"
//        registerScreen
//            .register(email = uniqueEmail, password = "qwerty123", age = "3")
//
//        profileScreen.waitEditButton()
//    }

}