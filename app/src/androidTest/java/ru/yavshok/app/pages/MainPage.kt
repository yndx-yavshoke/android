@file:OptIn(ExperimentalTestApi::class)

package ru.yavshok.app.pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags
import ru.yavshok.app.data.Data
import ru.yavshok.app.data.Timeouts

class MainPage(private val composeRule: ComposeTestRule) {
    val title = composeRule.onNodeWithTag(Tags.MainScreen.screenTitle)
    val emailTextField = composeRule.onNodeWithTag(Tags.MainScreen.emailTextField)
    val loginButton = composeRule.onNodeWithTag(Tags.MainScreen.loginButton)
    val checkButton = composeRule.onNodeWithTag(Tags.MainScreen.checkButton)
    val textExist = composeRule.onNodeWithTag(Tags.MainScreen.textExist)
    val textNotExist = composeRule.onNodeWithTag(Tags.MainScreen.textNotExist)
    val gif = composeRule.onNodeWithTag(Tags.MainScreen.gif)

    fun enterExistEmail() {
        with(emailTextField) {
            assertIsDisplayed()
            performTextInput(Data.EXIST_EMAIL)
            assertTextContains(Data.EXIST_EMAIL)
        }
    }

    fun enterNonExistEmail() {
        with(emailTextField) {
            assertIsDisplayed()
            performTextInput(Data.NON_EXIST_EMAIL)
            assertTextContains(Data.NON_EXIST_EMAIL)
        }
    }

    fun checkExistUser() {
        with(checkButton) {
            assertIsDisplayed()
            performClick()
        }

        composeRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.MainScreen.textExist),
            Timeouts.SHORT
        )
        textExist.assertIsDisplayed()
        gif.assertIsDisplayed()
    }

    fun checkNonExistUser() {
        with(checkButton) {
            assertIsDisplayed()
            performClick()
        }

        composeRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.MainScreen.textNotExist),
            Timeouts.SHORT
        )
        textNotExist.assertIsDisplayed()
        gif.assertDoesNotExist()
    }

    fun goToLogin() {
        with(loginButton) {
            assertIsDisplayed()
            performClick()
        }
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitExists() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = Timeouts.SHORT,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
    }
}
