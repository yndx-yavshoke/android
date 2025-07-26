package ru.yavshok.app.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class MainScreenPageObject(private val testRule: ComposeTestRule) {
    private val title = testRule.onNodeWithTag(Tags.MainScreen.screenTitle)
    private val emailField = testRule.onNodeWithTag(Tags.MainScreen.emailTextField)
    private val checkButton = testRule.onNodeWithTag(Tags.MainScreen.checkButton)
    private val toLoginButton = testRule.onNodeWithTag(Tags.MainScreen.loginButton)
    private val success = testRule.onNodeWithTag(Tags.MainScreen.successMessage)
    private val fail = testRule.onNodeWithTag(Tags.MainScreen.failureMessage)

    fun enterEmail(email: String): MainScreenPageObject {
        emailField.assertIsDisplayed()
        emailField.performTextInput(email)
        return this
    }

    fun clickCheck(): MainScreenPageObject {
        checkButton.assertIsDisplayed()
        checkButton.performClick()
        return this
    }

    fun clickToLogin(): MainScreenPageObject {
        toLoginButton.assertIsDisplayed()
        toLoginButton.performClick()
        return this
    }

    fun checkEmail(email: String): MainScreenPageObject {
        return enterEmail(email).clickCheck()
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitSuccessMessage(): MainScreenPageObject {
        testRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.MainScreen.successMessage),
            5000)
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitFailMessage(): MainScreenPageObject {
        testRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.MainScreen.failureMessage),
            5000)
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitTitle(): MainScreenPageObject {
        testRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.MainScreen.screenTitle),
            5000)
        return this
    }

    fun assertTitle(): MainScreenPageObject {
        title
            .assertIsDisplayed()
            .assertTextContains("Я в ШОКе")
        return this
    }

    fun assertEmailField(): MainScreenPageObject {
        emailField
            .assertIsDisplayed()
            .assertTextContains("Введите Email")
        return this
    }

    fun assertCheckButtonIsNotEnabled(): MainScreenPageObject {
        checkButton
            .assertIsDisplayed()
            .assertIsNotEnabled()
        return this
    }

    fun assertCheckButtonIsEnabled(): MainScreenPageObject {
        checkButton
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertTextContains("Я в шоке?")
        return this
    }

    fun assertLoginButton(): MainScreenPageObject {
        toLoginButton
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertTextContains("В шок")
        return this
    }

    fun assertSuccessMessage(): MainScreenPageObject {
        success
            .assertIsDisplayed()
            .assertTextContains("Ты уже в ШОКе")
        fail.assertDoesNotExist()
        return this
    }

    fun assertFailMessage(): MainScreenPageObject {
        fail
            .assertIsDisplayed()
            .assertTextContains("Ты еще не в ШОКе")
        success.assertDoesNotExist()
        return this
    }
}