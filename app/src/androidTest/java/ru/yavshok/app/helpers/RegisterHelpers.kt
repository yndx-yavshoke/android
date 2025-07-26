package ru.yavshok.helpers

import androidx.compose.ui.test.junit4.ComposeTestRule
import ru.yavshok.app.Tags

fun ComposeTestRule.register(email: String, password: String, age: String) {
    setInputText(Tags.RegisterScreen.emailTextField, email)
    setInputText(Tags.RegisterScreen.passwordTextField, password)
    setInputText(Tags.RegisterScreen.ageTextField, age)
    clickAndAssertExists(Tags.RegisterScreen.registerButton)
}