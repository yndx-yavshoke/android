package ru.yavshok.app.screens

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class MainScreenPage(private val composeTestRule: ComposeTestRule) {
    
    // Элементы с тегами
    private val screen = composeTestRule.onNodeWithTag(Tags.MainScreen.screenContainer)
    private val title = composeTestRule.onNodeWithTag(Tags.MainScreen.screenTitle)
    private val emailField = composeTestRule.onNodeWithTag(Tags.MainScreen.emailTextField)
    private val loginButton = composeTestRule.onNodeWithTag(Tags.MainScreen.loginButton)

    // Элементы без тегов (ищем по тексту)
    private val checkButton = composeTestRule.onNodeWithText(CHECK_BUTTON)
    private val successMessage = composeTestRule.onNodeWithText(SUCCESS_MESSAGE)
    private val errorMessage = composeTestRule.onNodeWithText(containsText(ERROR_MESSAGE), substring = true)

    fun verifyAllElementsVisible() {
        screen.assertExists()
        title.assertExists()
        emailField.assertExists()
        checkButton.assertExists()
        loginButton.assertExists()
    }

    fun enterEmail(email: String) {
        emailField.performTextInput(email)
    }

    fun clickCheckButton() {
        checkButton.performClick()
    }

    fun clickLoginButton() {
        loginButton.performClick()
    }

    fun verifySuccessState() {
        successMessage.assertExists()
    }

    fun verifyErrorState() {
        errorMessage.assertExists()
    }

    companion object {
        private const val TITLE_MAIN_SCREEN = "Я в ШОКе"
    }
}