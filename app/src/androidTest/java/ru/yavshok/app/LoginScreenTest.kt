package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertTextContains
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import  ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel
import org.junit.Test
import org.junit.Rule
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeRule = createComposeRule()
    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    @Test
    fun shouldTypeEmailOnLoginScreen() {
        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).performTextInput("my@email.ru")
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).assertTextContains("my@email.ru")

    }
}
