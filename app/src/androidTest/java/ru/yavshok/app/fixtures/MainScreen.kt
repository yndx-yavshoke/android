package ru.yavshok.app.fixtures

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import ru.yavshok.app.Tags


class MainScreen(private val rule: ComposeTestRule) {

    fun assertTitleIsDisplayed(): MainScreen {
        rule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        return this
    }

    fun assertTitleDoesNotExist(): MainScreen {
        rule.onNodeWithTag(Tags.MainScreen.screenTitle).assertDoesNotExist()
        return this
    }

    fun assertEmailFieldIsDisplayed(): MainScreen {
        rule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        return this
    }

    fun assertLoginButtonIsDisplayed(): MainScreen {
        rule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        return this
    }

    fun enterEmail(email: String): MainScreen {
        rule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput(email)
        return this
    }

    fun assertEmailFieldContains(text: String): MainScreen {
        rule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains(text)
        return this
    }

    fun clickCheckButton (): MainScreen {
        rule.onNodeWithTag(Tags.MainScreen.checkButton).performClick()
        return this
    }

    fun clickLoginButton (): MainScreen {
        rule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        return this
    }

    fun assertSuccessTextIsDisplayed(): MainScreen {
        rule.onNodeWithTag(Tags.MainScreen.successText).assertIsDisplayed()
        return this
    }

    fun assertHappyCatIsDisplayed(): MainScreen {
        rule.onNodeWithTag(Tags.MainScreen.happyCatImg).assertIsDisplayed()
        return this
    }

    fun assertUnsuccessTextIsDisplayed(): MainScreen{
        rule.onNodeWithTag(Tags.MainScreen.unsuccessText).assertIsDisplayed()
        return this
    }

    fun assertCheckButtonIsEnabled(): MainScreen {
        rule.onNodeWithTag(Tags.MainScreen.checkButton)
            .assertIsEnabled()
        return this
    }

    fun assertCheckButtonIsDisabled(): MainScreen {
        rule.onNodeWithTag(Tags.MainScreen.checkButton)
            .assertIsNotEnabled()
        return this
    }
}


