package ru.yavshok.app.fixtures

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class RegisterScreenPageObj(private val rule: ComposeContentTestRule) {

    private val screenTitle = Tags.RegisterScreen.screenTitle
    private val emailTextField = Tags.RegisterScreen.emailTextField
    private val passwordTextField = Tags.RegisterScreen.passwordTextField
    private val ageTextField = Tags.RegisterScreen.ageTextField
    private val registerButton = Tags.RegisterScreen.registerButton
    private val backButton = Tags.RegisterScreen.backButton


    fun waitForScreen(): RegisterScreenPageObj {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(screenTitle)
        )
        return this
    }

    fun assertTextIsVisible(expected: String): RegisterScreenPageObj {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 3_000L,
            matcher = hasTextExactly(expected)
        )
        return this
    }

    fun assertEmailFieldIsEmpty(): RegisterScreenPageObj {
        rule.onNodeWithTag(emailTextField).assertIsDisplayed()
        rule.onNodeWithTag(emailTextField).performTextClearance()
        return this
    }

    fun assertPasswordFieldIsEmpty(): RegisterScreenPageObj {
        rule.onNodeWithTag(passwordTextField).assertIsDisplayed()
        rule.onNodeWithTag(passwordTextField).performTextClearance()
        return this
    }

    fun assertAgeFieldIsEmpty(): RegisterScreenPageObj {
        rule.onNodeWithTag(ageTextField).assertIsDisplayed()
        rule.onNodeWithTag(ageTextField).performTextClearance()
        return this
    }

    fun inputEmail(email: String): RegisterScreenPageObj {
        rule.onNodeWithTag(emailTextField).assertIsDisplayed()
        rule.onNodeWithTag(emailTextField).performTextInput(email)
        return this
    }

    fun inputPassword(password: String): RegisterScreenPageObj {
        rule.onNodeWithTag(passwordTextField).assertIsDisplayed()
        rule.onNodeWithTag(passwordTextField).performTextInput(password)
        return this
    }

    fun inputAge(age: String): RegisterScreenPageObj {
        rule.onNodeWithTag(ageTextField).assertIsDisplayed()
        rule.onNodeWithTag(ageTextField).performTextInput(age)
        return this
    }

    fun clickOnRegisterButton(): RegisterScreenPageObj {
        Thread.sleep(1000L)
        rule.onNodeWithTag(registerButton).assertIsDisplayed()
        rule.onNodeWithTag(registerButton).performClick()
        return this
    }

    fun clickOnBackToLoginButton(): RegisterScreenPageObj {
        rule.onNodeWithTag(backButton).assertIsDisplayed()
        rule.onNodeWithTag(backButton).performClick()
        return this
    }

}