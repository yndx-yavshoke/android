package ru.yavshok.app.tests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Tags
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.ViewModelFactory

@OptIn(ExperimentalTextApi::class)
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    private val screenTitle = Tags.LoginScreen.screenTitle
    private val emailTextField = Tags.LoginScreen.emailTextField
    private val passwordTextField = Tags.LoginScreen.passwordTextField
    private val registerButton = Tags.LoginScreen.registerButton
    private val backButton = Tags.LoginScreen.backButton

    @get:Rule
    val composeRule = createComposeRule()
    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())
    val password = "cadabra"

    @Before
    fun setUp() {
        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }
    }

    @Test
    fun shouldDisplayAllElementsOnLoginScreen() {
        composeRule.onNodeWithTag(screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(passwordTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(registerButton).assertIsDisplayed()
        composeRule.onNodeWithTag(backButton).assertIsDisplayed()
    }

    @Test
    fun shouldTypeEmailOnLoginScreen() {
        composeRule.onNodeWithTag(emailTextField).performTextInput("abra@email.ru")
        composeRule.onNodeWithTag(emailTextField).assertTextContains("abra@email.ru")
    }

    @Test
    fun shouldTypePasswordOnLoginScreen() {
        composeRule.onNodeWithTag(passwordTextField).performTextInput(password)
        composeRule.onNodeWithTag(passwordTextField).assertTextEquals("•".repeat(password.length))
    }
}