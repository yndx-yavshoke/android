package ru.yavshok.app.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class MainScreen(private val rule: ComposeTestRule) {

    private val title get() = rule.onNodeWithTag(Tags.MainScreen.screenTitle)
    private val emailField get() = rule.onNodeWithTag(Tags.MainScreen.emailTextField)
    private val imInShokButton get() = rule.onNodeWithTag(Tags.MainScreen.imInShokButton)
    private val loginButton get() = rule.onNodeWithTag(Tags.MainScreen.loginButton)
    private val gifWithCat get() = rule.onNodeWithTag(Tags.MainScreen.gifWithCat)
    private val greenText get() = rule.onNodeWithTag(Tags.MainScreen.greenShokText)
    private val redText get() = rule.onNodeWithTag(Tags.MainScreen.redShokText)

    fun waitExists() {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 5000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
    }

    fun assertScreenDisplayed() {
        title.assertIsDisplayed()
        emailField.assertIsDisplayed()
        imInShokButton.assertIsDisplayed()
        loginButton.assertIsDisplayed()
    }

    fun typeEmail(email: String) {
        emailField.performTextInput(email)
    }

    fun clickOnImInShok() {
        imInShokButton.performClick()
    }


    fun isInShok() {
        rule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.MainScreen.gifWithCat),
            timeoutMillis = 5000L,
        )

        gifWithCat.assertIsDisplayed()
        greenText.assertIsDisplayed()
    }

    fun notIsInShok() {
        rule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.MainScreen.redShokText),
            timeoutMillis = 5000L,
        )

        redText.assertIsDisplayed()
    }

    fun clickOnLogin() {
        loginButton.performClick()
    }

}