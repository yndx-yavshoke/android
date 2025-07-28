package ru.yavshok.app.fixtures.screens

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

class MainScreenPage (private val composeRule: ComposeTestRule) {
    val title = composeRule.onNodeWithTag(Tags.MainScreen.screenTitle)
    val emailTextField = composeRule.onNodeWithTag(Tags.MainScreen.emailTextField)
    val loginButton = composeRule.onNodeWithTag(Tags.MainScreen.loginButton)
    val checkButton = composeRule.onNodeWithTag(Tags.MainScreen.checkButton)
    val emailExist = composeRule.onNodeWithTag(Tags.MainScreen.emailExist)
    val emailNotExist = composeRule.onNodeWithTag(Tags.MainScreen.emailNotExist)
    val catGif = composeRule.onNodeWithTag(Tags.MainScreen.catGif)

    fun checkTitleIsDisplayed() : MainScreenPage {
        title.assertIsDisplayed()
        return this
    }

    fun checkTitleTextIsDisplayed(expected: String = "Я в ШОКе"): MainScreenPage {
        title.assertTextContains(expected)
        return this
    }

    fun checkEmailPlaceholderIsDisplayed(): MainScreenPage {
        emailTextField.assertIsDisplayed()
        emailTextField.assertTextContains("Введите Email")
        return this
    }

    fun checkLoginButtonIsDisplayed(): MainScreenPage {
        loginButton.assertIsDisplayed()
        loginButton.assertTextContains("В шок")
        return this
    }

    fun checkCheckButtonIsDisplayed(): MainScreenPage {
        checkButton.assertIsDisplayed()
        checkButton.assertTextContains("Я в шоке?")
        return this
    }

    fun checkCheckButtonEnabled(): MainScreenPage {
        checkButton.assertIsEnabled()
        return this
    }

    fun checkCheckButtonDisabled(): MainScreenPage {
        checkButton.assertIsNotEnabled()
        return this
    }

    fun typeEmail(email: String) : MainScreenPage {
        emailTextField.performTextInput(email)
        return this
    }

    fun clickCheckButton() : MainScreenPage  {
        checkButton.performClick()
        return this
    }

    fun clickLogin() : MainScreenPage {
        loginButton.performClick()
        return this
    }

    fun checkEmailStatus(email: String): MainScreenPage {
        checkEmailPlaceholderIsDisplayed()
        return typeEmail(email).clickCheckButton()
    }

    @OptIn(ExperimentalTestApi::class)
    fun checkEmailExistsMessage() : MainScreenPage {

        composeRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.MainScreen.emailExist),
            5_000L
        )
        emailExist.assertTextContains("Ты уже в ШОКе")
        catGif.assertIsDisplayed()
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun checkEmailNotExistMessage() : MainScreenPage {
        composeRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.MainScreen.emailNotExist),
            5_000L
        )
        emailNotExist.assertTextContains("Ты еще не в ШОКе")
        catGif.assertDoesNotExist()
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitExistTitle() : MainScreenPage {
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.MainScreen.screenTitle),
            timeoutMillis = 5_000L
        )
        return this
    }
}