package ru.yavshok.app.Pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class LoginScreenPage(private val rule: ComposeTestRule) {

    private val title = Tags.LoginScreen.screenTitle
    private val emailTextField = Tags.LoginScreen.screenEmailTextField
    private val passwordTextField = Tags.LoginScreen.screenPasswordTextField
    private val buttonInShock = Tags.LoginScreen.screenEnterButton
    private val buttonBack = Tags.LoginScreen.screenBackButton
    private val buttonRegister = Tags.LoginScreen.screnRegisterButton
    private val placeholderEmail = "Email"
    private val placeholderPassword = "Пароль"
    private val emptyError = "Заполните все поля"
    private val errorEmailOrPassword = "Неверный email или пароль"
    private val errorEmail = "Неверный формат email"
    private val messageError = Tags.LoginScreen.errorMessage


    fun timeoutTitle() = apply {
        rule.waitUntilExactlyOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(title)
        )
    }

    fun displayedTitleLoginScreen() = apply {
        rule.onNodeWithTag(title).assertIsDisplayed()
    }

    fun displayedEmailTextField() = apply {
        rule.onNodeWithTag(emailTextField).assertIsDisplayed()
    }

    fun inputEmail(email: String) = apply {
        rule.onNodeWithTag(emailTextField).performTextInput(email)
        rule.onNodeWithTag(emailTextField).assertTextContains(email)
    }

    fun displayedPasswordTextField() = apply {
        rule.onNodeWithTag(passwordTextField).assertIsDisplayed()
    }

    fun inputPassword(password: String) = apply {
        rule.onNodeWithTag(passwordTextField).performTextInput(password)
    }

    fun displayedButtonInShock() = apply {
        rule.onNodeWithTag(buttonInShock).assertIsDisplayed()
    }

    fun displayedButtonBack() = apply {
        rule.onNodeWithTag(buttonBack).assertIsDisplayed()
    }

    fun displayedButtonRegister() = apply {
        rule.onNodeWithTag(buttonRegister).assertIsDisplayed()
    }

    fun displayedError() = apply {
        rule.onNodeWithTag(messageError).assertIsDisplayed()
    }

    fun errorEmpty() = apply {
        rule.onNodeWithTag(messageError).assertTextContains(emptyError)
    }

    fun errorEmailOrPassword() = apply {
        rule.onNodeWithTag(messageError).assertTextContains(errorEmailOrPassword)
    }

    fun errorEmail() = apply {
        rule.onNodeWithTag(messageError).assertTextContains(errorEmail)
    }

    fun emailTextFieldHasPlaceholder() = apply {
        rule.onNodeWithTag(emailTextField).assertTextContains(placeholderEmail)
    }

    fun passwordTextFieldHasPlaceholder() = apply {
        rule.onNodeWithTag(passwordTextField).assertTextContains(placeholderPassword)
    }

    fun clickButtonInShock() = apply {
        rule.onNodeWithTag(buttonInShock).performClick()
    }

    fun clickButtonBack() = apply {
        rule.onNodeWithTag(buttonBack).performClick()
    }

    fun clickButtonRegister() = apply {
        rule.onNodeWithTag(buttonRegister).performClick()
    }
}