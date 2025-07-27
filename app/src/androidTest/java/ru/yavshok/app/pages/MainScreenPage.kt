package ru.yavshok.app.pages

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import ru.yavshok.app.Tags
import androidx.compose.ui.test.performTextInput

class MainScreenPage(
    private val composeRule: ComposeTestRule
) {
    private val title = Tags.MainScreen.screenTitle
    private val mailInput = Tags.MainScreen.emailTextField
    private val checkButton = Tags.MainScreen.checkButton
    private val loginPageButton = Tags.MainScreen.loginButton
    private val successText = Tags.MainScreen.successText
    private val happyGif = Tags.MainScreen.happyGif
    private val failText = Tags.MainScreen.failText

    fun assertTitleIsDisplayed() = apply {
        composeRule.onNodeWithTag(title).assertIsDisplayed()
    }

    fun enterEmail(email: String) = apply {
        composeRule.onNodeWithTag(mailInput)
            .assertExists()

        composeRule.onNodeWithTag(mailInput)
            .performTextClearance()

        composeRule.onNodeWithTag(mailInput)
            .performTextInput(email)
    }

    fun clickCheckButton() = apply {
        composeRule.onNodeWithTag(checkButton)
            .assertIsDisplayed()
            .performClick()
    }

    fun clickLoginPageButton() = apply {
        composeRule.onNodeWithTag(loginPageButton)
            .assertIsDisplayed()
            .performClick()
    }

    fun assertSuccessTextIsVisible() = apply {
        composeRule.onNodeWithTag(successText).assertIsDisplayed()
    }

    fun assertGifIsVisible() = apply {
        composeRule.onNodeWithTag(happyGif).assertIsDisplayed()
    }

    fun assertFailTextIsVisible(expectedText: String) = apply {
        composeRule.onNodeWithTag(failText)
            .assertIsDisplayed()
            .assertTextContains(expectedText)
    }
    fun timeoutForTitle(timeoutMillis: Long = 5_000L): MainScreenPage = apply {
        composeRule.waitUntil(timeoutMillis) {
            composeRule.onAllNodesWithTag(Tags.MainScreen.screenTitle)
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
    }
}
