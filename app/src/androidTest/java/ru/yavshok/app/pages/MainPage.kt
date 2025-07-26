package ru.yavshok.app.pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags


@OptIn(ExperimentalTestApi::class)
class MainPage(composeRule: ComposeTestRule) : BasePage(composeRule) {

    private val title = Tags.MainScreen.screenTitle
    private val emailTextField = Tags.MainScreen.emailTextField
    private val existButton = Tags.MainScreen.existButton
    private val loginButton = Tags.MainScreen.loginButton
    private val successMessage = Tags.MainScreen.successMessage
    private val failureMessage = Tags.MainScreen.failureMessage

    override val nodeIsDisplayed = mutableListOf(
        title,
        emailTextField,
        existButton,
        loginButton
    )

    fun fillEmail(email: String){
        composeRule.onNodeWithTag(emailTextField).performTextInput(email)
    }

    fun existButtonIsEnabled(){
        composeRule.onNodeWithTag(existButton).assertIsEnabled()
    }

    fun existButtonIsDisabled(){
        composeRule.onNodeWithTag(existButton).assertIsNotEnabled()
    }

    fun clickExistButton(){
        composeRule.onNodeWithTag(existButton).performClick()
    }

    fun clickToLoginButton(){
        composeRule.onNodeWithTag(loginButton).performClick()
    }

    fun successMessageIsDisplayed(){
        composeRule.waitUntilExactlyOneExists(
            hasTestTag(successMessage),
            1_000L
        )
    }

    fun failureMessageIsDisplayed(){
        composeRule.waitUntilExactlyOneExists(
            hasTestTag(failureMessage),
            1_000L
        )
    }

}
