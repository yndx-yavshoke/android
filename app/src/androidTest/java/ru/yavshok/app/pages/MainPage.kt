package ru.yavshok.app.pages
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import ru.yavshok.app.Tags
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithText
import ru.yavshok.app.ui.screens.MainScreen


class MainPage(composeTestRule: AndroidComposeTestRule<*, *>)
    : BasePage(composeTestRule) {
    val title = composeTestRule.onNodeWithTag(Tags.MainScreen.screenTitle)
    val emailField = composeTestRule.onNodeWithTag(Tags.MainScreen.emailTextField)
    val checkButton = composeTestRule.onNodeWithTag(Tags.MainScreen.checkButton)
    val loginButton = composeTestRule.onNodeWithTag(Tags.MainScreen.loginButton)
    val existText= composeTestRule.onNodeWithTag(Tags.MainScreen.existText)
    val notExistText = composeTestRule.onNodeWithTag(Tags.MainScreen.notExistText)

    @OptIn(ExperimentalTestApi::class)
    fun waitForTitle() {
        composeTestRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle))
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitForExistText() {
        composeTestRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.existText))

    }

    @OptIn(ExperimentalTestApi::class)
    fun waitForNotExistText() {
        composeTestRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.notExistText))

    }

    fun checkEmail(email: String): MainPage {
        title.assertIsDisplayed()
        emailField.assertIsDisplayed()
            .performTextInput(email)
        checkButton.performClick()
        return this
    }

    fun clickLoginButton(): LoginPage {
        //  composeTestRule.onNodeWithText("В шок").performClick()
        loginButton.performClick()
        return LoginPage(composeTestRule)
    }


}