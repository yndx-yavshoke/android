package ru.yavshok.app.screens.login

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.testdata.TestConfig
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.LoginViewModel

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var loginScreenPage: LoginScreenPage

    @Test
    fun verifyAllElementsDisplayed() {
        launchScreen()
        loginScreenPage.verifyAllElementsVisible()
    }

    @Test
    fun testSuccessfulLogin() {
        var loginSuccessTriggered = false
        launchScreen(onLoginSuccess = { loginSuccessTriggered = true })
        
        loginScreenPage.apply {
            enterEmail(TestConfig.Credentials.VALID_EMAIL)
            enterPassword(TestConfig.Credentials.PASSWORD)
            clickLoginButton()
        }
        
        assert(loginSuccessTriggered)
    }

    @Test
    fun testEmptyEmailError() {
        launchScreen()
        
        loginScreenPage.apply {
            enterPassword(TestConfig.Credentials.PASSWORD)
            clickLoginButton()
            verifyEmptyEmailError()
        }
    }

    @Test
    fun testEmptyPasswordError() {
        launchScreen()
        
        loginScreenPage.apply {
            enterEmail(TestConfig.Credentials.VALID_EMAIL)
            clickLoginButton()
            verifyEmptyPasswordError()
        }
    }

    @Test
    fun testWrongCredentialsError() {
        launchScreen()
        
        loginScreenPage.apply {
            enterEmail(TestConfig.Credentials.INVALID_EMAIL)
            enterPassword("wrong_password")
            clickLoginButton()
            verifyWrongCredentialsError()
        }
    }

    @Test
    fun testBackButtonNavigation() {
        var backNavigationTriggered = false
        launchScreen(onNavigateBack = { backNavigationTriggered = true })
        
        loginScreenPage.clickBackButton()
        assert(backNavigationTriggered)
    }

    @Test
    fun testRegisterButtonNavigation() {
        var registerNavigationTriggered = false
        launchScreen(onNavigateToRegister = { registerNavigationTriggered = true })
        
        loginScreenPage.clickRegisterButton()
        assert(registerNavigationTriggered)
    }

    @Test
    fun testLoadingState() {
        launchScreen()
        
        loginScreenPage.apply {
            enterEmail(TestConfig.Credentials.VALID_EMAIL)
            enterPassword(TestConfig.Credentials.PASSWORD)
            clickLoginButton()
            verifyLoadingIndicatorVisible()
        }
    }

    private fun launchScreen(
        onNavigateToRegister: () -> Unit = {},
        onNavigateBack: () -> Unit = {},
        onLoginSuccess: () -> Unit = {}
    ) {
        composeTestRule.setContent {
            LoginScreen(
                onNavigateToRegister = onNavigateToRegister,
                onNavigateBack = onNavigateBack,
                onLoginSuccess = onLoginSuccess,
                viewModel = viewModel()
            )
        }
        loginScreenPage = LoginScreenPage(composeTestRule)
    }
}