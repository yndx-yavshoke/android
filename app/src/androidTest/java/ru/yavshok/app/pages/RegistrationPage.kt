package ru.yavshok.app.pages
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class RegistrationPage(composeTestRule: AndroidComposeTestRule<*, *>)
    : BasePage(composeTestRule) {
    val title = composeTestRule.onNodeWithTag(Tags.RegisterScreen.screenTitle)
    val emailField = composeTestRule.onNodeWithTag(Tags.RegisterScreen.emailTextField)
    val passwordField = composeTestRule.onNodeWithTag(Tags.RegisterScreen.passwordTextField)
    val ageField = composeTestRule.onNodeWithTag(Tags.RegisterScreen.ageTextField)
    val regButton = composeTestRule.onNodeWithTag(Tags.RegisterScreen.registerButton)
    val backButton = composeTestRule.onNodeWithTag(Tags.RegisterScreen.backButton)
    val errorText = composeTestRule.onNodeWithTag(Tags.RegisterScreen.errorText)


    @OptIn(ExperimentalTestApi::class)
    fun waitForTitle() {
        composeTestRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle)
        )
    }


    fun verifyOnPage(): RegistrationPage {
        composeTestRule.onNodeWithText("Регистрация в ШОКе").assertIsDisplayed()
        return this
    }



    fun writeEmailPasswordAge(email: String, password: String, age: String): RegistrationPage {
        title.assertIsDisplayed()
        emailField.assertIsDisplayed()
            .performTextInput(email)
        passwordField.performTextInput(password)
        ageField.performTextInput(age)

        return this
    }

    fun clickRegisterButton(): RegistrationPage {
        title.assertIsDisplayed()
        regButton.performClick()
        return this
    }

    fun clickBackButton(): RegistrationPage {
        title.assertIsDisplayed()
        backButton.performClick()
        return this
    }
}