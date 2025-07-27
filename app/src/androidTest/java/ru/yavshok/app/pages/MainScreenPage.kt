package ru.yavshok.app.pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import ru.yavshok.app.Tags

class MainScreenPage (private val composeRule: ComposeTestRule) {
    public val title = composeRule.onNodeWithTag("main_screen.screen_title")
    public val emailInput = composeRule.onNodeWithTag("main_screen.email_text_field")
    public val checkRegistrationButton = composeRule.onNodeWithTag("main_screen.check_button")
    public val goToLoginButton = composeRule.onNodeWithTag("main_screen.login_button")
    public val successText = composeRule.onNodeWithTag("main_screen.success_message")
    public val errorText = composeRule.onNodeWithTag("main_screen.error_message")

    @OptIn(ExperimentalTestApi::class)
    fun waitForTitle(): MainScreenPage {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        return this
    }

    fun fillEmailInput(email: String): MainScreenPage {
        emailInput.assertIsDisplayed()
        emailInput.performTextInput(email);
        return this
    }

    fun clickCheckButton(): MainScreenPage {
        checkRegistrationButton.assertIsDisplayed()
        checkRegistrationButton.performClick()
        return this
    }

    fun clickGoToLoginButton(): MainScreenPage {
        goToLoginButton.assertIsDisplayed()
        goToLoginButton.performClick()
        return this
    }
}

fun SemanticsNodeInteraction.exists(): Boolean {
    return try {
        this.assertIsDisplayed()
        true
    } catch (e: AssertionError) {
        false
    }
}

