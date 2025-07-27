package ru.yavshok.app.pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import ru.yavshok.app.Tags

class RegisterScreenPage (private val composeRule: ComposeTestRule) {
    public val title = composeRule.onNodeWithTag("register_screen.screen_title")
    public val emailInput = composeRule.onNodeWithTag("register_screen.email_text_field")
    public val passwordInput = composeRule.onNodeWithTag("register_screen.password_text_field")
    public val ageInput = composeRule.onNodeWithTag("register_screen.age_field")
    public val registerButton = composeRule.onNodeWithTag("register_screen.register_button")
    public val backButton = composeRule.onNodeWithTag("register_screen.back_button")

    @OptIn(ExperimentalTestApi::class)
    fun waitForTitle(): RegisterScreenPage {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle)
        )
        return this
    }

    fun fillEmailInput(email: String): RegisterScreenPage {
        emailInput.assertIsDisplayed()
        emailInput.performTextInput(email);
        return this
    }

    fun fillPasswordInput(password: String): RegisterScreenPage {
        passwordInput.assertIsDisplayed()
        passwordInput.performTextInput(password);
        return this
    }

    fun fillAgeInput(age: String): RegisterScreenPage {
        ageInput.assertIsDisplayed()
        ageInput.performTextInput(age);
        return this
    }

    fun clickRegisterButton(): RegisterScreenPage {
        registerButton.assertIsDisplayed()
        registerButton.performClick()
        return this
    }

    fun clickBackButton(): RegisterScreenPage {
        backButton.assertIsDisplayed()
        backButton.performClick()
        return this
    }

    fun fullRegister(email: String, password: String, age: String): RegisterScreenPage {
        return fillEmailInput(email).fillPasswordInput(password).fillAgeInput(age).clickRegisterButton()
    }
}