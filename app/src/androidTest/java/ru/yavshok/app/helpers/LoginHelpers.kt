package ru.yavshok.helpers

import androidx.compose.ui.test.junit4.ComposeTestRule
import ru.yavshok.app.Tags

fun ComposeTestRule.login(email: String, password: String) {
    setInputText(Tags.LoginScreen.emailTextField, email)
    setInputText(Tags.LoginScreen.passwordTextField, password)
    clickAndAssertExists(Tags.LoginScreen.loginButton)
}