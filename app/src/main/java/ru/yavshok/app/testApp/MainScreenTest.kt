package ru.yavshok.app.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.testdata.TestConfig
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel

class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mainScreenPage: MainScreenPage

    @Test
    fun verifyAllMainScreenElementsVisible() {
        launchScreen()
        mainScreenPage.verifyAllElementsVisible()
    }

    @Test
    fun testValidEmailInput() {
        launchScreen()
        
        mainScreenPage.apply {
            enterEmail(TestConfig.TEST_EMAIL)
            clickCheckButton()
            verifySuccessState()
        }
    }

    @Test
    fun testInvalidEmailInput() {
        launchScreen()
        
        mainScreenPage.apply {
            enterEmail(TestConfig.TEST_INVALID_EMAIL)
            clickCheckButton()
            verifyErrorState()
        }
    }

    @Test
    fun testLoginButtonNavigation() {
        var navigationTriggered = false
        
        launchScreen(onNavigateToLogin = { navigationTriggered = true })
        
        mainScreenPage.clickLoginButton()
        assert(navigationTriggered)
    }

    private fun launchScreen(onNavigateToLogin: () -> Unit = {}) {
        composeTestRule.setContent {
            MainScreen(
                onNavigateToLogin = onNavigateToLogin,
                viewModel = viewModel() 
            )
        }
        mainScreenPage = MainScreenPage(composeTestRule)
    }
}