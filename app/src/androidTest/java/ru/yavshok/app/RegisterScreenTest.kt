package ru.yavshok.app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.qameta.allure.kotlin.junit4.DisplayName
import kotlinx.coroutines.test.TestScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.screens.ProfileScreenPage
import ru.yavshok.app.fixtures.screens.RegisterScreenPage
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.utils.TestData
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
    @DisplayName("Регистрация: Отображение всех UI-элементов")
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
    @DisplayName("Регистрация: Ошибка при неверном email")
    fun shouldShowErrorIfEmailInvalidOnRegisterScreen() {
        registerScreen
            .register(TestData.INVALID_EMAIL, TestData.VALID_PASSWORD, TestData.VALID_AGE)
            .waitErrorMessage()
            .checkErrorText(TestData.ERROR_EMAIL_FORMAT)
    }

    @Test
    @DisplayName("Регистрация: Ошибка при незаполненных полях")
    fun shouldShowErrorIfFieldsAreEmptyOnRegisterScreen() {
        registerScreen
            .clickRegister()
            .waitErrorMessage()
            .checkErrorText(TestData.ERROR_EMPTY_FIELDS)
    }

    @Test
    @DisplayName("Регистрация: Ошибка если пользователь уже существует")
    fun shouldShowErrorIfUserAlreadyExistsOnRegisterScreen() {
        registerScreen
            .register(TestData.EXISTING_EMAIL, TestData.VALID_PASSWORD, TestData.VALID_AGE)
            .waitErrorMessage()
            .checkErrorText(TestData.ERROR_USER_EXISTS)
    }

    @Test
    @DisplayName("Регистрация: Ошибка если возраст введён не числом")
    fun shouldShowErrorIfAgeNotNumberOnRegisterScreen() {
        registerScreen
            .register(TestData.NEW_EMAIL, TestData.VALID_PASSWORD, TestData.INVALID_AGE)
            .waitErrorMessage()
            .checkErrorText(TestData.ERROR_AGE_FORMAT)
    }

    @Test
    @DisplayName("Регистрация: Ошибка если пароль слишком короткий")
    fun shouldShowErrorIfPasswordTooShortOnRegisterScreen() {
        registerScreen
            .register(TestData.NEW_EMAIL, TestData.SHORT_PASSWORD, TestData.VALID_AGE)
            .waitErrorMessage()
            .checkErrorText(TestData.ERROR_PASSWORD_SHORT)
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