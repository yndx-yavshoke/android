package ru.yavshok.app.tests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags
import ru.yavshok.app.pages.MainScreenPage
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel

@OptIn(ExperimentalTextApi::class)
@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val mainScreen = MainScreenPage(composeRule)
    private val email = "1@email.ru"

    @Before
    fun setUp(){
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
    }

    @Test
    fun shouldDisplayAllElements() {
        mainScreen
            .displayScreenTitle()
            .displayLoginButton()
            .displayEmailTextField()
    }

    @Test
    fun shouldTypeEmailOnMainScreen() {
        mainScreen
            .displayScreenTitle()
            .displayEmailTextField()
            .typeEmail(email)
            .emailIsContain(email)
    }
}