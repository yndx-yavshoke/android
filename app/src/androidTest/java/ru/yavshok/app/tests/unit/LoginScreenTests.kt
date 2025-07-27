package ru.yavshok.app.tests.unit

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.Tags
import ru.yavshok.app.base.BaseComposeTest
import ru.yavshok.app.pages.LoginPage
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.utils.TestConfig
import ru.yavshok.app.viewmodel.LoginViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory

class LoginScreenTests : BaseComposeTest() {

    @get:Rule
    override val composeTestRule = createComposeRule()

    private lateinit var loginPage: LoginPage
    private lateinit var loginViewModel: LoginViewModel

    @Before
    override fun setup() {
        super.setup()
        composeTestRule.mainClock.autoAdvance = true

        // Создаём ViewModel через фабрику
        val factory = ViewModelFactory(ApplicationProvider.getApplicationContext())
        loginViewModel = factory.create(LoginViewModel::class.java)

        loginPage = LoginPage(composeTestRule)
    }

    private fun getDefaultPage(): LoginPage {
        loginPage.setLoginScreenContent(viewModel = loginViewModel)
        composeTestRule.waitForIdle()
        return loginPage
    }

    private fun getCleanPage(): LoginPage {
        val page = getDefaultPage()
        page.clearAllFields()
        composeTestRule.waitForIdle()
        return page
    }

    // ПРОВЕРКА ОТОБРАЖЕНИЯ ЭЛЕМЕНТОВ НА СТРАНИЦЕ
    @Test
    fun shouldShowTitleOnScreen() {
        getDefaultPage().assertScreenTitleIsDisplayed()
    }

    @Test
    fun shouldShowEmailFieldOnScreen() {
        getDefaultPage().assertEmailTextFieldIsDisplayed()
    }

    @Test
    fun shouldShowPasswordFieldOnScreen() {
        getDefaultPage().assertPasswordTextFieldIsDisplayed()
    }

    @Test
    fun shouldShowLoginButtonOnScreen() {
        getDefaultPage().assertLoginButtonIsDisplayed()
    }

    @Test
    fun shouldShowBackButtonOnScreen() {
        getDefaultPage().assertBackButtonIsDisplayed()
    }

    @Test
    fun shouldShowGoToRegisterButtonOnScreen() {
        getDefaultPage().assertGoToRegisterButtonIsDisplayed()
    }

    // Проверка ошибки пустых полей
    @Test
    fun shouldShowErrorForAllEmptyField(){
        getCleanPage().run {
            clickLoginButton()
            waitForErrorMessage()
            assertErrorMessageContains("Заполните все поля")
            clearPasswordField()
        }
    }

    // Проверка ошибки пустого email
    @Test
    fun shouldShowErrorForEmptyEmail() {
        getCleanPage().run {
            enterPassword(TestConfig.wrongPassword)
            clickLoginButton()
            waitForErrorMessage()
            assertErrorMessageContains("Заполните все поля")
            clearPasswordField()
        }
    }

    // Проверка ошибки пустого пароля
    @Test
    fun shouldShowErrorForEmptyPassword() {
        getCleanPage().run {
            enterEmail(TestConfig.notRegisteredEmail)
            clickLoginButton()
            waitForErrorMessage()
            assertErrorMessageContains("Заполните все поля")
            clearEmailField()
        }
    }

    // Проверка ошибки входа с неверными данными
    @Test
    fun shouldShowErrorForInvalidCredentials() {
        getCleanPage().run {
            enterEmail(TestConfig.notRegisteredEmail)
            enterPassword(TestConfig.wrongPassword)
            clickLoginButton()
            waitForErrorMessage()
            assertErrorMessageContains("Неверный email или пароль")
        }
    }
}