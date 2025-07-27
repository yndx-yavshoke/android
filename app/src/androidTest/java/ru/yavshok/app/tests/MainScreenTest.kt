package ru.yavshok.app.tests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Tags
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel

@OptIn(ExperimentalTextApi::class)
@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    private val screenTitle = Tags.MainScreen.screenTitle
    private val emailTextField = Tags.MainScreen.emailTextField

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun shouldTypeEmailOnMainScreen() {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
        composeRule.onNodeWithTag(screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(emailTextField).performTextInput("1@email.ru")
        composeRule.onNodeWithTag(emailTextField).assertTextContains("1@email.ru")
    }
}