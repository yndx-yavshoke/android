package ru.yavshok.app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.qameta.allure.kotlin.junit4.DisplayName
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.screens.LoginScreenPage
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.utils.TestData
import ru.yavshok.app.viewmodel.LoginViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val loginScreen by lazy { LoginScreenPage(composeRule) }

    private lateinit var onLoginSuccess: () -> Unit

    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())
    val loginViewModel = vmFactory.create(LoginViewModel::class.java)

    @Before
    fun setup() {
        onLoginSuccess = {}
        composeRule.setContent {
            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = onLoginSuccess
            )
        }
    }

    @Test
    @DisplayName("Авторизация: проверка отображения всех UI элементов")
    fun shouldDisplayAllUIElementsWithCorrectTextsOnLoginScreen() {
        loginScreen
            .waitExistTitle()
            .checkTitleIsDisplayed()
            .checkTitleTextIsDisplayed()
            .checkEmailPlaceholderIsDisplayed()
            .checkPasswordPlaceholderIsDisplayed()
            .checkLoginButtonIsDisplayed()
            .checkBackButtonIsDisplayed()
            .checkRegisterButtonIsDisplayed()
    }

    @Test
    @DisplayName("Авторизация: показать ошибку при пустых полях")
    fun shouldShowErrorForEmptyFieldsOnLoginScreen() {
        loginScreen
            .waitExistTitle()
            .clickLogin()
            .waitErrorMessage()
            .checkErrorText(TestData.ERROR_EMPTY_FIELDS)
    }

    @Test
    @DisplayName("Авторизация: показать ошибку при неверном формате email")
    fun shouldShowErrorForInvalidEmailFormatOnLoginScreen() {
        loginScreen
            .waitExistTitle()
            .login(TestData.INVALID_EMAIL, TestData.VALID_PASSWORD)
            .waitErrorMessage()
            .checkErrorText(TestData.ERROR_EMAIL_FORMAT)
    }

    @Test
    @DisplayName("Авторизация: показать ошибку при несуществующем пользователе")
    fun shouldShowErrorForNonExistentUserOnLoginScreen() {
        loginScreen
            .waitExistTitle()
            .login(TestData.NEW_EMAIL, TestData.VALID_PASSWORD)
            .waitErrorMessage()
            .checkErrorText(TestData.ERROR_EMAIL_PASSWORD_NOT_EXISTS)
    }
}