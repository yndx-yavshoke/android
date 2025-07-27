package ru.yavshok.app.fixtures

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags
import ru.yavshok.app.utility.ApplicationTextEnv

class LoginScreenFixture(private val testRule: ComposeTestRule) {
    private val title = testRule.onNodeWithTag(Tags.LoginScreen.screenTitle)
    private val emailTextField = testRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
    private val passwordTextField = testRule.onNodeWithTag(Tags.LoginScreen.passwordTextField)
    private val backButton = testRule.onNodeWithTag(Tags.LoginScreen.backButton)
    private val loginButton = testRule.onNodeWithTag(Tags.LoginScreen.loginButton)
    private val registrationButton = testRule.onNodeWithTag(Tags.LoginScreen.registrationButton)

    fun checkTitle(): LoginScreenFixture {
        title.assertIsDisplayed()
        title.assertTextContains(ApplicationTextEnv.LoginScreen.title)
        return this
    }

    fun checkEmailTextField(): LoginScreenFixture {
        emailTextField.assertIsDisplayed()
        emailTextField.assertTextContains(ApplicationTextEnv.LoginScreen.emailTextField)
        return this
    }

    fun checkPasswordTextField(): LoginScreenFixture {
        passwordTextField.assertIsDisplayed()
        passwordTextField.assertTextContains(ApplicationTextEnv.LoginScreen.passwordTextField)
        return this
    }

    fun checkBackButton(): LoginScreenFixture {
        backButton.assertIsDisplayed()
        backButton.assertTextContains(ApplicationTextEnv.LoginScreen.loginButton)
        return this
    }

    fun checkRegistrationButton(): LoginScreenFixture {
        registrationButton.assertIsDisplayed()
        registrationButton.assertTextContains(ApplicationTextEnv.LoginScreen.registrationButton)
        return this
    }

    fun enterEmailCredentials(email: String): LoginScreenFixture {
        emailTextField.assertIsDisplayed()
        emailTextField.performTextInput(email)
        return this
    }

    fun enterPasswordCredentials(password: String): LoginScreenFixture {
        passwordTextField.assertIsDisplayed()
        passwordTextField.performTextInput(password)
        return this
    }

    fun clickLoginButton(): LoginScreenFixture {
        loginButton.assertIsDisplayed()
        loginButton.performClick()
        return this
    }

    fun clickBackButton(): LoginScreenFixture {
        backButton.assertIsDisplayed()
        backButton.performClick()
        return this
    }

    fun loginIntoSystem(email: String, password: String): LoginScreenFixture {
        return enterEmailCredentials(email).enterPasswordCredentials(password).clickLoginButton()
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitScreenTitle(): LoginScreenFixture {
        testRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.LoginScreen.screenTitle)
        )
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitErrorMessage(message: String): LoginScreenFixture {
        testRule.waitUntilAtLeastOneExists(
            hasTextExactly(message), 5000
        )
        return this
    }
}