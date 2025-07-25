package ru.yavshok.app.fixtures

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags


@OptIn(ExperimentalTestApi::class)
class LoginScreenPageObj(private val rule: ComposeContentTestRule) {

    private val screenTitle = Tags.LoginScreen.screenTitle
    private val emailTextField = Tags.LoginScreen.emailTextField
    private val passwordTextField = Tags.LoginScreen.passwordTextField
    private val loginButton = Tags.LoginScreen.loginButton
    private val toRegisterButton = Tags.LoginScreen.registerButton
    private val backButton = Tags.LoginScreen.backButton


    fun waitForScreen(): LoginScreenPageObj {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(screenTitle)
        )
        return this
    }

    fun assertTextIsVisible(expected: String): LoginScreenPageObj {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 3_000L,
            matcher = hasTextExactly(expected)
        )
        return this
    }

    fun assertEmailFieldIsEmpty(): LoginScreenPageObj {
        rule.onNodeWithTag(emailTextField).assertIsDisplayed()
        rule.onNodeWithTag(emailTextField).performTextClearance()
        return this
    }

    fun assertPasswordFieldIsEmpty(): LoginScreenPageObj {
        rule.onNodeWithTag(passwordTextField).assertIsDisplayed()
        rule.onNodeWithTag(passwordTextField).performTextClearance()
        return this
    }

    fun inputEmail(email: String): LoginScreenPageObj {
        rule.onNodeWithTag(emailTextField).assertIsDisplayed()
        rule.onNodeWithTag(emailTextField).performTextInput(email)
        return this
    }

    fun inputPassword(password: String): LoginScreenPageObj {
        rule.onNodeWithTag(passwordTextField).assertIsDisplayed()
        rule.onNodeWithTag(passwordTextField).performTextInput(password)
        return this
    }

    fun clickOnLoginButton(): LoginScreenPageObj {
        Thread.sleep(1000L)
        rule.onNodeWithTag(loginButton).assertIsDisplayed()
        rule.onNodeWithTag(loginButton).performClick()
        return this
    }

    fun clickOnToRegisterButton(): LoginScreenPageObj {
        rule.onNodeWithTag(toRegisterButton).assertIsDisplayed()
        rule.onNodeWithTag(toRegisterButton).performClick()
        return this
    }

    fun clickOnBackButton(): LoginScreenPageObj {
        rule.onNodeWithTag(backButton).assertIsDisplayed()
        rule.onNodeWithTag(backButton).performClick()
        return this
    }

}
