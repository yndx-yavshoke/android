package ru.yavshok.app.pages

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.Tags
import ru.yavshok.app.viewmodel.MainViewModel

class MainPage(private val composeTestRule: ComposeContentTestRule) {

    fun setMainScreenContent(
        viewModel: MainViewModel = MainViewModel(),
        onNavigateToLogin: () -> Unit = {}
    ) {
        composeTestRule.setContent {
            MainScreen(
                onNavigateToLogin = onNavigateToLogin,
                viewModel = viewModel
            )
        }
    }

    // ЭЛЕМЕНТЫ СТРАНИЦЫ
    fun assertScreenTitleIsDisplayed() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.screenTitle)
            .assertIsDisplayed()
    }

    fun assertEmailTextFieldIsDisplayed() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.emailTextField)
            .assertIsDisplayed()
    }

    fun assertEmailTextFieldPlaceholder() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.emailTextField)
            .assertTextContains("Введите Email")
    }

    fun assertCheckButtonIsDisplayed() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.checkButton)
            .assertIsDisplayed()
    }

    fun assertLoginButtonIsDisplayed() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.loginButton)
            .assertIsDisplayed()
    }

    // ОЧИСТКА ПОЛЯ ПОЧТЫ
    fun clearEmailField() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.emailTextField)
            .performTextClearance()
    }

    // ФУНКЦИОНАЛЬНОСТЬ
    fun enterEmail(email: String) {
        composeTestRule.onNodeWithTag(Tags.MainScreen.emailTextField)
            .performTextInput(email)
    }

    fun clickCheckButton() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.checkButton).performClick()
    }

    fun clickLoginButton() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.loginButton)
            .performClick()
    }

    // ПРОВЕРКИ РЕЗУЛЬТАТОВ

    fun waitForLoginSuccess(timeoutMillis: Long = 5000) {
        composeTestRule.waitUntil(timeoutMillis) {
            try {
                composeTestRule.onNodeWithTag(Tags.MainScreen.loginSuccessText).assertIsDisplayed()
                true
            } catch (e: AssertionError) {
                false
            }
        }
    }

    fun waitForLoginError(timeoutMillis: Long = 5000) {
        composeTestRule.waitUntil(timeoutMillis) {
            try {
                composeTestRule.onNodeWithTag(Tags.MainScreen.loginWrongText).assertIsDisplayed()
                true
            } catch (e: AssertionError) {
                false
            }
        }
    }
    fun assertLoginSuccessTextIsDisplayed() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.loginSuccessText)
            .assertIsDisplayed()
    }

    fun assertLoginWrongTextIsDisplayed() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.loginWrongText)
            .assertIsDisplayed()
    }

    fun assertCheckButtonIsEnabled() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.checkButton)
            .assertIsEnabled()
    }

    fun assertLoginButtonIsEnabled() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.loginButton)
            .assertIsEnabled()
    }
    fun testNavigation(navigateToLogin: () -> Unit): MainPage {
        setMainScreenContent(onNavigateToLogin = navigateToLogin)
        return this
    }
}