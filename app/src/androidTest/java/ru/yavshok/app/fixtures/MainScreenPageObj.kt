package ru.yavshok.app.fixtures

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import ru.yavshok.app.Tags


@OptIn(ExperimentalTestApi::class)
class MainScreenPageObj(private val rule: ComposeContentTestRule) {

    private val screenTitle = Tags.MainScreen.screenTitle
    private val emailTextField = Tags.MainScreen.emailTextField
    private val toLoginButton = Tags.MainScreen.loginButton
    private val checkEmailButton = Tags.MainScreen.checkEmailButton


    fun waitForScreen(): MainScreenPageObj {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(screenTitle)
        )
        return this
    }

    fun inputEmail(email: String): MainScreenPageObj {
        rule.onNodeWithTag(emailTextField).assertIsDisplayed()
        rule.onNodeWithTag(emailTextField).performTextInput(email)
        return this
    }

    fun assertEmailFieldIsEmpty(): MainScreenPageObj {
        rule.onNodeWithTag(emailTextField).assertIsDisplayed()
        rule.onNodeWithTag(emailTextField).performTextClearance()
        return this
    }

    fun assertTextIsVisible(expected: String): MainScreenPageObj {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 3_000L,
            matcher = hasTextExactly(expected)
        )
        return this
    }

    fun assertTextIsNotVisible(expected: String): MainScreenPageObj {
        Thread.sleep(1000L)
        rule.onNodeWithText(expected).assertDoesNotExist()
        return this
    }

    fun clickOnToLoginButton(): MainScreenPageObj {
        rule.onNodeWithTag(toLoginButton).assertIsDisplayed()
        rule.onNodeWithTag(toLoginButton).performClick()
        return this
    }

    fun clickOnCheckEmailButton(): MainScreenPageObj {
        Thread.sleep(1000L)
        rule.onNodeWithTag(checkEmailButton).assertIsDisplayed()
        rule.onNodeWithTag(checkEmailButton).performClick()
        return this
    }

}