package ru.yavshok.app.Fixtures

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class MainPage(private val rule: ComposeTestRule) {

    fun assertTitleIsDisplayed(): MainPage {
        rule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        return this
    }

    fun assertTitleDoesNotExist(): MainPage {
        rule.onNodeWithTag(Tags.MainScreen.screenTitle).assertDoesNotExist()
        return this
    }

    fun assertEmailFieldIsDisplayed(): MainPage {
        rule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        return this
    }

    fun assertLoginButtonIsDisplayed(): MainPage {
        rule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        return this
    }

    fun enterEmail(email: String): MainPage {
        rule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput(email)
        return this
    }

    fun assertEmailFieldContains(text: String): MainPage {
        rule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains(text)
        return this
    }

    fun clickCheckButton (): MainPage {
        rule.onNodeWithTag(Tags.MainScreen.checkButton).performClick()
        return this
    }

    fun clickLoginButton (): MainPage {
        rule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        return this
    }

    fun assertSuccessTextIsDisplayed(): MainPage {
        rule.onNodeWithTag(Tags.MainScreen.EmailExistsTxt).assertIsDisplayed()
        return this
    }

    fun assertSuccessIMGIsDisplayed(): MainPage {
        rule.onNodeWithTag(Tags.MainScreen.EmailExistsIMG).assertIsDisplayed()
        return this
    }

    fun assertUnsuccessTextIsDisplayed(): MainPage{
        rule.onNodeWithTag(Tags.MainScreen.notEmailExistsTxt).assertIsDisplayed()
        return this
    }

}