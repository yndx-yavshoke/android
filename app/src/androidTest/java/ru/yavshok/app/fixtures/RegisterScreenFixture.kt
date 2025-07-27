package ru.yavshok.app.fixtures

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasClickAction
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

class RegisterScreenFixture(private val testRule: ComposeTestRule) {
    private val title = testRule.onNodeWithTag(Tags.RegistrationScreen.screenTitle)
    private val emailTextField = testRule.onNodeWithTag(Tags.RegistrationScreen.emailTextField)
    private val passwordTextField = testRule.onNodeWithTag(Tags.RegistrationScreen.passwordTextField)
    private val ageTextField = testRule.onNodeWithTag(Tags.RegistrationScreen.ageTextField)
    private val registrationButton = testRule.onNodeWithTag(Tags.RegistrationScreen.registrationButton)
    private val backButton = testRule.onNodeWithTag(Tags.RegistrationScreen.backButton)
    private val errorMessage = testRule.onNodeWithTag(Tags.RegistrationScreen.errorMessage)

    fun checkTitle(): RegisterScreenFixture {
        title.assertIsDisplayed()
        title.assertTextContains(ApplicationTextEnv.RegistrationScreen.title)
        return this
    }

    fun checkEmailTextField(): RegisterScreenFixture {
        emailTextField.assertIsDisplayed()
        emailTextField.assertTextContains(ApplicationTextEnv.RegistrationScreen.emailTextField)
        return this
    }

    fun checkPasswordTextField(): RegisterScreenFixture {
        passwordTextField.assertIsDisplayed()
        passwordTextField.assertTextContains(ApplicationTextEnv.RegistrationScreen.passwordTextField)
        return this
    }

    fun checkAgeTextField(): RegisterScreenFixture {
        ageTextField.assertIsDisplayed()
        ageTextField.assertTextContains(ApplicationTextEnv.RegistrationScreen.ageTextField)
        return this
    }

    fun checkRegistrationButton(): RegisterScreenFixture {
        registrationButton.assertIsDisplayed()
        registrationButton.assertTextContains(ApplicationTextEnv.RegistrationScreen.registrationButton)
        return this
    }

    fun checkBackButton(): RegisterScreenFixture {
        backButton.assertIsDisplayed()
        backButton.assertTextContains(ApplicationTextEnv.RegistrationScreen.backButton)
        return this
    }

    fun enterEmailCredentials(email: String): RegisterScreenFixture {
        emailTextField.assertIsDisplayed()
        emailTextField.performClick()
        emailTextField.performTextInput(email)
        return this
    }

    fun enterPasswordCredentials(password: String): RegisterScreenFixture {
        passwordTextField.assertIsDisplayed()
        passwordTextField.performClick()
        passwordTextField.performTextInput(password)
        return this
    }

    fun enterAgeCredentials(age: String): RegisterScreenFixture {
        ageTextField.assertIsDisplayed()
        ageTextField.performClick()
        ageTextField.performTextInput(age)
        return this
    }

    fun clickRegistrationButton(): RegisterScreenFixture {
        registrationButton.assertIsDisplayed()
        registrationButton.performClick()
        return this
    }

    fun clickBackButton(): RegisterScreenFixture {
        backButton.assertIsDisplayed()
        backButton.performClick()
        return this
    }

    fun registerProcess(email: String, password: String, age: String): RegisterScreenFixture {
        return enterEmailCredentials(email)
            .enterPasswordCredentials(password)
            .enterAgeCredentials(age)
            .clickRegistrationButton()
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitScreenTitle(): RegisterScreenFixture {
        testRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.RegistrationScreen.screenTitle), 5000
        )
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitErrorMessage(message: String): RegisterScreenFixture {
        testRule.waitUntilAtLeastOneExists(
            hasTextExactly(message), 5000
        )
        return this
    }
}