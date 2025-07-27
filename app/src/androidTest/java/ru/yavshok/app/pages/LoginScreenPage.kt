package ru.yavshok.app.pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import ru.yavshok.app.Tags


class LoginScreenPage(private val rule: ComposeTestRule) {

    fun typeEmail(email: String) {
        rule.onNodeWithTag(Tags.LoginScreen.emailTextField).performTextInput(email)
    }

    fun typePassword(password: String) {
        rule.onNodeWithTag(Tags.LoginScreen.passwordTextField).performTextInput(password)
    }

    fun clickSubmit() {
        rule.onNodeWithTag(Tags.LoginScreen.submitButton).performClick()
    }

    fun clickBack() {
        rule.onNodeWithTag(Tags.LoginScreen.goBackButton).performClick()
    }

    fun clickRegister() {
        rule.onNodeWithTag(Tags.LoginScreen.goToRegisterButton).performClick()
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitForErrorAndAssert(expectedText: String) {
        rule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.LoginScreen.loginError),
            timeoutMillis = 5_000
        )
        rule.onNodeWithTag(Tags.LoginScreen.loginError)
            .assertIsDisplayed()
            .assertTextContains(expectedText)
    }

    fun assertAllElementsVisible() {
        rule.onNodeWithTag(Tags.LoginScreen.emailTextField).assertIsDisplayed()
        rule.onNodeWithTag(Tags.LoginScreen.passwordTextField).assertIsDisplayed()
        rule.onNodeWithTag(Tags.LoginScreen.submitButton).assertIsDisplayed()
        rule.onNodeWithTag(Tags.LoginScreen.goBackButton).assertIsDisplayed()
        rule.onNodeWithTag(Tags.LoginScreen.goToRegisterButton).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitForLoginForm(timeoutMillis: Long = 5_000) {
        rule.waitUntil(timeoutMillis) {
            rule.onAllNodesWithTag(Tags.LoginScreen.emailTextField)
                .fetchSemanticsNodes().isNotEmpty()
        }
    }
}
