package ru.yavshok.app.fixtures

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class RegisterScreenFixture(private val testRule: ComposeTestRule) {
    private val title = testRule.onNodeWithTag(Tags.RegistrationScreen.screenTitle)
    private val emailTextField = testRule.onNodeWithTag(Tags.RegistrationScreen.emailTextField)
    private val passwordTextField = testRule.onNodeWithTag(Tags.RegistrationScreen.passwordTextField)
    private val ageTextField = testRule.onNodeWithTag(Tags.RegistrationScreen.ageTextField)
    private val registrationButton = testRule.onNodeWithTag(Tags.RegistrationScreen.ageTextField)
    private val backButton = testRule.onNodeWithTag(Tags.RegistrationScreen.backButton)

    fun enterEmailCredentials(email: String): RegisterScreenFixture {
        emailTextField.assertIsDisplayed()
        emailTextField.performClick()
        emailTextField.performTextInput(email)
        return this
    }

    fun enterPasswordCredentials(password: String): RegisterScreenFixture {
        passwordTextField.assertIsDisplayed()
        passwordTextField.performClick()
        emailTextField.performTextInput(password)
        return this
    }

    fun enterAgeCredentials(age: String): RegisterScreenFixture {
        ageTextField.assertIsDisplayed()
        ageTextField.performClick()
        ageTextField.performTextInput(age)
        return this
    }

    fun registerProcess(email: String, password: String, age: String): RegisterScreenFixture {
        return enterEmailCredentials(email).enterPasswordCredentials(password).enterAgeCredentials(age).clickRegisterButton()
    }

    fun clickRegisterButton(): RegisterScreenFixture {
        registrationButton.assertIsDisplayed()
        registrationButton.performClick()
        return this
    }

    fun clickBackButton(): RegisterScreenFixture {
        backButton.assertIsDisplayed()
        backButton.performClick()
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun checkTitle(): RegisterScreenFixture {
        testRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.RegistrationScreen.screenTitle), 5000)
        return this
    }

    fun checkEmailTextInput(): RegisterScreenFixture {
        emailTextField.assertIsDisplayed()
        return this
    }


}