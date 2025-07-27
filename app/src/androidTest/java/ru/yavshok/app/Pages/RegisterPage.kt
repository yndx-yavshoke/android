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
class RegisterScreenPage(private val rule: ComposeTestRule) {
    private val title = Tags.RegisterScreen.screenTitle
    private val emailTextField = Tags.RegisterScreen.emailTextField
    private val passwordTextField = Tags.RegisterScreen.passwordTextField
    private val ageTextField = Tags.RegisterScreen.ageTextField
    private val buttonRegister = Tags.RegisterScreen.registerButton
    private val buttonBack = Tags.RegisterScreen.backButton
    private val placeholderEmail = "Введите email"
    private val placeholderPassword = "Пароль"
    private val placeholderAge = "Возраст"
    private val emptyError = "Заполните все поля"
    private val repeatEmailError = "Пользователь с таким email уже существует"
    private val passwordError = "Пароль должен содержать от 5 до 20 символов"
    private val ageError = "Возраст должен быть от 0 до 99 лет"
    private val emailError = "Неверный формат email"
    private val errorMessage = Tags.RegisterScreen.errorMessage

    fun displayedTitle() = apply {
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
//        rule.onNodeWithTag(passwordTextField).assertTextContains(password)
    }

    fun displayedAgeTextField() = apply {
        rule.onNodeWithTag(ageTextField).assertIsDisplayed()
    }

    fun inputAge(age: String) = apply {
        rule.onNodeWithTag(ageTextField).performTextInput(age)
        rule.onNodeWithTag(ageTextField).assertTextContains(age)
    }

    fun displayedButtonRegister() = apply {
        rule.onNodeWithTag(buttonRegister).assertIsDisplayed()
    }

    fun displayedButtonBack() = apply {
        rule.onNodeWithTag(buttonBack).assertIsDisplayed()
    }

    fun emailTextFieldHasPlaceholder() = apply {
        rule.onNodeWithTag(emailTextField).assertTextContains(placeholderEmail)
    }

    fun passwordTextFieldHasPlaceholder() = apply {
        rule.onNodeWithTag(passwordTextField).assertTextContains(placeholderPassword)
    }

    fun ageTextFieldHasPlaceholder() = apply {
        rule.onNodeWithTag(ageTextField).assertTextContains(placeholderAge)
    }

    fun clickRegisterButton() = apply {
        rule.onNodeWithTag(buttonRegister).performClick()
    }

    fun clickBackButton() = apply {
        rule.onNodeWithTag(buttonBack).performClick()
    }

    fun displayedError() = apply {
        rule.onNodeWithTag(errorMessage).assertIsDisplayed()
    }

    fun errorEmpty() = apply {
        rule.onNodeWithTag(errorMessage).assertTextContains(emptyError)
    }

    fun errorEmail() = apply {
        rule.onNodeWithTag(errorMessage).assertTextContains(emailError)
    }

    fun errorPassword() = apply {
        rule.onNodeWithTag(errorMessage).assertTextContains(passwordError)
    }

    fun errorAge() = apply {
        rule.onNodeWithTag(errorMessage).assertTextContains(ageError)
    }

    fun errorRepeatEmail() = apply {
        rule.onNodeWithTag(errorMessage).assertTextContains(repeatEmailError)
    }

    fun timeoutForTitle() = apply {
        rule.waitUntilExactlyOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(title)
        )
    }


}