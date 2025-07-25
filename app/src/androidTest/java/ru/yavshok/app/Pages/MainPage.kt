package ru.yavshok.app.Pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class MainScreenPage(private val rule: ComposeTestRule) {

    private val title = Tags.MainScreen.screenTitle
    private val emailTextField = Tags.MainScreen.emailTextField
    private val checkButton = Tags.MainScreen.checkLoginButton
    private val loginButton = Tags.MainScreen.loginButton
    private val lineEmailTrue = Tags.MainScreen.lineCheckEmailTrue
    private val lineEmailFalse = Tags.MainScreen.lineCheckEmailFalse
    private val imageCheckEmail = Tags.MainScreen.imageCheckEmailTru
    private val placeholderEmailTextField = "Введите Email"


    fun displayedTitle() = apply {
        rule.onNodeWithTag(title).assertIsDisplayed()
    }

    fun notDisplayedTitle() = apply {
        rule.onNodeWithTag(title).assertIsNotDisplayed()
    }

    fun displayedEmailTextField() = apply {
        rule.onNodeWithTag(emailTextField).assertIsDisplayed()
    }

    fun displayedCheckButton() = apply {
        rule.onNodeWithTag(checkButton).assertIsDisplayed()
    }

    fun displayedLoginButton() = apply {
        rule.onNodeWithTag(loginButton).assertIsDisplayed()
    }

    fun displayedLineEmailTrue() = apply {
        rule.onNodeWithTag(lineEmailTrue).assertIsDisplayed()
    }

    fun displayedLineEmailFalse() = apply {
        rule.onNodeWithTag(lineEmailFalse).assertIsDisplayed()
    }

    fun displayedImageCheckEmail() = apply {
        rule.onNodeWithTag(imageCheckEmail).assertIsDisplayed()
    }

    fun inputEmailInTextField(email: String) = apply {
        rule.onNodeWithTag(emailTextField).performTextInput(email)
    }

    fun clearEmailTextField() = apply {
        rule.onNodeWithTag(emailTextField).performTextClearance()
    }

    fun clickCheckButton() = apply {
        rule.onNodeWithTag(checkButton).performClick()
    }

    fun clickLoginButton() = apply {
        rule.onNodeWithTag(loginButton).performClick()
    }

    fun emailTextFieldHasPlaceholder() = apply {
        rule.onNodeWithTag(emailTextField).assertTextContains(placeholderEmailTextField)
    }


    fun verifeInputTextInEmailTextField(str: String) = apply {
        inputEmailInTextField(str)
        rule.onNodeWithTag(emailTextField).assertTextContains(str)
    }

    fun enableCheckButton() = apply {
        rule.onNodeWithTag(checkButton).assertIsEnabled()
    }

    fun notEnableCheckButton() = apply {
        rule.onNodeWithTag(checkButton).assertIsNotEnabled()
    }

    fun timeoutFotTitle() = apply {
        rule.waitUntilExactlyOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(title)
        )
    }

    fun timeoutForGif() = apply {
        rule.waitUntilExactlyOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(imageCheckEmail)
        )
    }

    fun timeoutForFalseLine() = apply {
        rule.waitUntilExactlyOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(lineEmailFalse)
        )
    }


}