package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.MainViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory


@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get: Rule
    val composeRule = createComposeRule()

    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    @Test
    fun shouldDisplayAllElementsOnLoginScreen() {
        composeRule.setContent { LoginScreen(
            viewModel = viewModel(factory = vmFactory)
        ) }

        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.emailField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.backButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.regButton).assertIsDisplayed()

        composeRule.onNodeWithTag(Tags.LoginScreen.emailField).assertTextContains("Email")
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordField).assertTextContains("Пароль")
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertTextEquals("Войти в ШОК")

        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertTextEquals("В шок")
        composeRule.onNodeWithTag(Tags.LoginScreen.backButton).assertTextEquals("Назад")
        composeRule.onNodeWithTag(Tags.LoginScreen.regButton).assertTextEquals("Регистрация")

    }

    @Test
    fun shouldTypeEmailOnLoginScreen() {
        composeRule.setContent { LoginScreen(
            viewModel = viewModel(factory = vmFactory)
        ) }

        composeRule.onNodeWithTag(Tags.LoginScreen.emailField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.emailField).performTextInput("a@ya.ru")

        composeRule.onNodeWithTag(Tags.LoginScreen.emailField).assertTextContains("a@ya.ru")
    }

    @Test
    fun shouldDisplayErrorOnNoEmail() {
        composeRule.setContent { LoginScreen(
            viewModel = viewModel(factory = vmFactory)
        ) }

        composeRule.onNodeWithTag(Tags.LoginScreen.passwordField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordField).performTextInput("123456")
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()

        composeRule.onNodeWithTag(Tags.LoginScreen.errorMessage).assertIsDisplayed()

    }

    @Test
    fun shouldDisplayErrorOnNoPassword() {
        composeRule.setContent { LoginScreen(
            viewModel = viewModel(factory = vmFactory)
        ) }

        composeRule.onNodeWithTag(Tags.LoginScreen.emailField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.emailField).performTextInput("a@ya.ru")
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()

        composeRule.onNodeWithTag(Tags.LoginScreen.errorMessage).assertIsDisplayed()

    }

    @Test
    fun shouldDisplayErrorOnIncorrectEmail() {
        composeRule.setContent { LoginScreen(
            viewModel = viewModel(factory = vmFactory)
        ) }

        composeRule.onNodeWithTag(Tags.LoginScreen.emailField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.emailField).performTextInput("aaaa")
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordField).performTextInput("123456")
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()

        composeRule.onNodeWithTag(Tags.LoginScreen.errorMessage).assertIsDisplayed()

    }
}