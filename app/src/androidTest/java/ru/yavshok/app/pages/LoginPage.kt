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

class LoginPage(composeTestRule: AndroidComposeTestRule<*, *>)
    : BasePage(composeTestRule){
    val title = composeTestRule.onNodeWithTag(Tags.LoginScreen.screenTitle)
    val emailField = composeTestRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
    val passwordField = composeTestRule.onNodeWithTag(Tags.LoginScreen.passwordTextField)
    val loginButton = composeTestRule.onNodeWithTag(Tags.LoginScreen.loginButton)
    val registerButton = composeTestRule.onNodeWithTag(Tags.LoginScreen.registerButton)
    val backButton = composeTestRule.onNodeWithTag(Tags.LoginScreen.backButton)
    val errortext = composeTestRule.onNodeWithTag(Tags.LoginScreen.errorText)

    @OptIn(ExperimentalTestApi::class)
    fun waitForTitle() {
        composeTestRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )
    }



    fun clickRegisterButton(): RegistrationPage {
        //title.assertIsDisplayed()
   //     composeTestRule.onNodeWithText("Регистрация").performClick()
        registerButton.performClick()
        return RegistrationPage(composeTestRule)
        }

    fun writeEmailPassword(email: String, password: String): LoginPage {
        title.assertIsDisplayed()
        emailField.assertIsDisplayed()
            .performTextInput(email)
        passwordField.performTextInput(password)


        return this
    }

    fun clickBackButton(): LoginPage {
        title.assertIsDisplayed()
        backButton.performClick()
        return this
    }

    fun clickLoginButton(): LoginPage {
        title.assertIsDisplayed()
        loginButton.performClick()
        return this
    }





}




//class LoginPage {

  //  fun clickRegisterLink(): RegisterPage {
   //     registerLink.performClick()
   //     return RegisterPage()
   // }
//}