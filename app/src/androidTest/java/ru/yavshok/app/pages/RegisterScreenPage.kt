package ru.yavshok.app.pages

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import ru.yavshok.app.Tags

class RegisterScreenPage(private val rule: ComposeTestRule) {

    fun typeEmail(email: String) {
        rule.onNodeWithTag(Tags.RegisterScreen.emailField).performTextInput(email)
    }

    fun typePassword(password: String) {
        rule.onNodeWithTag(Tags.RegisterScreen.passwordField).performTextInput(password)
    }

    fun typeAge(age: String) {
        rule.onNodeWithTag(Tags.RegisterScreen.ageField).performTextInput(age)
    }

    fun clickSubmit() {
        rule.onNodeWithTag(Tags.RegisterScreen.submitButton).performClick()
    }

    fun clickBack() {
        rule.onNodeWithTag(Tags.RegisterScreen.backButton).performClick()
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitForErrorAndAssert(expectedText: String) {
        rule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.RegisterScreen.errorText),
            timeoutMillis = 5_000
        )
        rule.onNodeWithTag(Tags.RegisterScreen.errorText)
            .assertIsDisplayed()
            .assertTextContains(expectedText)
    }

    fun assertAllElementsDisplayed() {
        rule.onNodeWithTag(Tags.RegisterScreen.emailField).assertIsDisplayed()
        rule.onNodeWithTag(Tags.RegisterScreen.passwordField).assertIsDisplayed()
        rule.onNodeWithTag(Tags.RegisterScreen.ageField).assertIsDisplayed()
        rule.onNodeWithTag(Tags.RegisterScreen.submitButton).assertIsDisplayed()
        rule.onNodeWithTag(Tags.RegisterScreen.backButton).assertIsDisplayed()
    }



}
