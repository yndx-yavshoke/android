package ru.yavshok.app.modules

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class LoginPage (private val rule: ComposeTestRule){
    private val title = Tags.LoginScreen.screenTitle
    private val emailTextField = Tags.LoginScreen.emailTextField
    private val passwordTextField = Tags.LoginScreen.passwordTextField
    private val loginButton = Tags.LoginScreen.loginButton
    private val returnButton = Tags.LoginScreen.returnButton
    private val registerButton = Tags.LoginScreen.registerButton
    private val fieldError  = Tags.LoginScreen.fieldError

    fun isVisibleTitle() {
        rule.onNodeWithTag(title).assertIsDisplayed()
    }

    fun isVisibleEmailTextField() {
        rule.onNodeWithTag(emailTextField).assertIsDisplayed()
    }

    fun isVisiblePasswordTextField() {
        rule.onNodeWithTag(passwordTextField).assertIsDisplayed()
    }

    fun isVisibleLoginButton() {
        rule.onNodeWithTag(loginButton).assertIsDisplayed()
    }

    fun isVisibleReturnButton() {
        rule.onNodeWithTag(returnButton).assertIsDisplayed()
    }

    fun isVisibleRegisterButton() {
        rule.onNodeWithTag(registerButton).assertIsDisplayed()
    }


    @OptIn(ExperimentalTestApi::class)
    fun isVisibleFieldError() {
        rule.waitUntilAtLeastOneExists(timeoutMillis = 5000L, matcher =  hasTestTag(fieldError))
        rule.onNodeWithTag(fieldError).assertIsDisplayed()
    }

    fun fillEmail(email: String){
        rule.onNodeWithTag(emailTextField).performTextInput(email)
    }

    fun fillPassword(password: String){
        rule.onNodeWithTag(passwordTextField).performTextInput(password)
    }

    fun clickLogin(){
        rule.onNodeWithTag(loginButton).performClick()
    }

    fun clickReturn(){
        rule.onNodeWithTag(returnButton).performClick()
    }

    fun clickRegister(){
        rule.onNodeWithTag(registerButton).performClick()
    }

    fun emailIs(value: String){
        rule.onNodeWithTag(emailTextField).assertTextEquals(value)
    }

    fun passwordIs(value: String){
        rule.onNodeWithTag(passwordTextField).assertTextEquals(value)
    }

    fun errorIs(value: String){
        rule.onNodeWithTag(fieldError).assertTextEquals(value)
    }
}