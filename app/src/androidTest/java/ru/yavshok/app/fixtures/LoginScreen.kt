package ru.yavshok.app.fixtures

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags



class LoginScreen (private val rule: ComposeTestRule) {

    fun assertTitleIsDisplayed(): LoginScreen {
        rule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        return this
    }

    fun assertTitleDoesNotExist(): LoginScreen {
        rule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()
        return this
    }

    fun assertEmailFieldIsDisplayed(): LoginScreen {
        rule.onNodeWithTag(Tags.LoginScreen.emailTextField).assertIsDisplayed()
        return this
    }

    fun assertEmailFieldContainsText(email: String): LoginScreen {
        rule.onNodeWithTag(Tags.LoginScreen.emailTextField).assertTextContains(email)
        return this
    }

    fun assertPasswordFieldIsDisplayed(): LoginScreen {
        rule.onNodeWithTag(Tags.LoginScreen.passwordTextField).assertIsDisplayed()
        return this
    }

    fun assertPasswordFieldContainsText(text: String): LoginScreen {
        rule.onNodeWithTag(Tags.LoginScreen.passwordTextField).assertTextContains(text)
        return this
    }

    fun assertLoginButtonIsDisplayed(): LoginScreen {
        rule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
        return this
    }

    fun assertBackButtonIsDisplayed(): LoginScreen {
        rule.onNodeWithTag(Tags.LoginScreen.backButton).assertIsDisplayed()
        return this
    }

    fun assertRegisterButtonIsDisplayed(): LoginScreen {
        rule.onNodeWithTag(Tags.LoginScreen.registerButton).assertIsDisplayed()
        return this
    }

    fun assertErrorMessageIsDisplayed(): LoginScreen {
        rule.onNodeWithTag(Tags.LoginScreen.errorMessage).assertIsDisplayed()
        return this
    }


    fun enterEmail(email: String): LoginScreen {
        rule.onNodeWithTag(Tags.LoginScreen.emailTextField).performTextInput(email)
        return this
    }


    fun enterPassword(password: String): LoginScreen {
        rule.onNodeWithTag(Tags.LoginScreen.passwordTextField).performTextInput(password)
        return this
    }

    fun clickLoginButton(): LoginScreen {
        rule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
        return this
    }

    fun clickBackButton(): LoginScreen {
        rule.onNodeWithTag(Tags.LoginScreen.backButton).performClick()
        return this
    }

    fun clickRegisterButton(): LoginScreen {
        rule.onNodeWithTag(Tags.LoginScreen.registerButton).performClick()
        return this
    }
}

