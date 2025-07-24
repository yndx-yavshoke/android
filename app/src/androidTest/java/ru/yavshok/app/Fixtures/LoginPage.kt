package ru.yavshok.app.Fixtures

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags



class LoginPage (private val rule: ComposeTestRule) {

    fun assertTitleIsDisplayed(): LoginPage {
        rule.onNodeWithTag(Tags.LoginPage.screenTitle).assertIsDisplayed()
        return this
    }

    fun assertTitleDoesNotExist(): LoginPage {
        rule.onNodeWithTag(Tags.LoginPage.screenTitle).assertDoesNotExist()
        return this
    }

    fun assertEmailFieldIsDisplayed(): LoginPage {
        rule.onNodeWithTag(Tags.LoginPage.emailTxtField).assertIsDisplayed()
        return this
    }

    fun assertEmailFieldContainsText(email: String): LoginPage {
        rule.onNodeWithTag(Tags.LoginPage.emailTxtField).assertTextContains(email)
        return this
    }

    fun assertPasswordFieldIsDisplayed(): LoginPage {
        rule.onNodeWithTag(Tags.LoginPage.passwordTxtField).assertIsDisplayed()
        return this
    }

    fun assertPasswordFieldContainsText(text: String): LoginPage {
        rule.onNodeWithTag(Tags.LoginPage.passwordTxtField).assertTextContains(text)
        return this
    }

    fun assertLoginButtonIsDisplayed(): LoginPage {
        rule.onNodeWithTag(Tags.LoginPage.loginButton).assertIsDisplayed()
        return this
    }

    fun assertBackButtonIsDisplayed(): LoginPage {
        rule.onNodeWithTag(Tags.LoginPage.backButton).assertIsDisplayed()
        return this
    }

    fun assertRegisterButtonIsDisplayed(): LoginPage {
        rule.onNodeWithTag(Tags.LoginPage.registerButton).assertIsDisplayed()
        return this
    }

    fun assertErrorMessageIsDisplayed(): LoginPage {
        rule.onNodeWithTag(Tags.LoginPage.errorMessage).assertIsDisplayed()
        return this
    }


    fun enterEmail(email: String): LoginPage {
        rule.onNodeWithTag(Tags.LoginPage.emailTxtField).performTextInput(email)
        return this
    }


    fun enterPassword(password: String): LoginPage {
        rule.onNodeWithTag(Tags.LoginPage.passwordTxtField).performTextInput(password)
        return this
    }

    fun clickLoginButton(): LoginPage {
        rule.onNodeWithTag(Tags.LoginPage.loginButton).performClick()
        return this
    }

    fun clickBackButton(): LoginPage {
        rule.onNodeWithTag(Tags.LoginPage.backButton).performClick()
        return this
    }

    fun clickRegisterButton(): LoginPage {
        rule.onNodeWithTag(Tags.LoginPage.registerButton).performClick()
        return this
    }
}
