package ru.yavshok.app.screens.register

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.testdata.TestConfig
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.viewmodel.RegisterViewModel

class RegisterScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var registerScreenPage: RegisterScreenPage

    @Test
    fun verifyAllElementsDisplayed() {
        launchScreen()
        registerScreenPage.verifyAllElementsVisible()
    }

    @Test
    fun testSuccessfulRegistration() {
        var registrationSuccess = false
        launchScreen(onRegistrationSuccess = { registrationSuccess = true })
        
        registerScreenPage.apply {
            enterRegisterCredentials()
            clickRegisterButton()
        }
        
        assert(registrationSuccess)
    }

    @Test
    fun testBackButtonNavigation() {
        var backNavigationTriggered = false
        launchScreen(onNavigateBack = { backNavigationTriggered = true })
        
        registerScreenPage.clickBackButton()
        assert(backNavigationTriggered)
    }

    private fun launchScreen(
        onNavigateBack: () -> Unit = {},
        onRegistrationSuccess: () -> Unit = {}
    ) {
        composeTestRule.setContent {
            RegisterScreen(
                onNavigateBack = onNavigateBack,
                onRegistrationSuccess = onRegistrationSuccess,
                viewModel = viewModel()
            )
        }
        registerScreenPage = RegisterScreenPage(composeTestRule)
    }
}