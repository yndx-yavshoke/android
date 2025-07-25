package ru.yavshok.app.tests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixture.fixture
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel

@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get: Rule
    val composeRule = createComposeRule()

    @Test
    fun shouldTypeEmailINTextField() = fixture(composeRule) {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }

        with(mainPage) {
            title.assertIsDisplayed()
            enterNonExistEmail()
        }
    }

    @Test
    fun shouldShowThatUserExist() = fixture(composeRule) {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }

        with(mainPage) {
            title.assertIsDisplayed()
            enterExistEmail()
            checkExistUser()
        }
    }

    @Test
    fun shouldShowThatUserNotExist() = fixture(composeRule) {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }

        with(mainPage) {
            title.assertIsDisplayed()
            enterNonExistEmail()
            checkNonExistUser()
        }
    }
}