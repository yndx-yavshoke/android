package ru.yavshok.app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.screens.MainScreenPage
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel

@RunWith(AndroidJUnit4::class)
class MainScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    private val mainScreen by lazy { MainScreenPage(composeRule) }

    @Before
    fun setup() {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
    }

    @Test
    fun shouldDisplayAllUIElementsWithCorrectTextsOnMainScreen() {
        mainScreen
            .waitExistTitle()
            .checkTitleIsDisplayed()
            .checkTitleTextIsDisplayed()
            .checkEmailPlaceholderIsDisplayed()
            .checkCheckButtonIsDisplayed()
            .checkCheckButtonDisabled()
            .checkLoginButtonIsDisplayed()
    }

    @Test
    fun shouldEnableCheckButtonAfterTypingEmailOnMainScreen() {
        mainScreen
            .waitExistTitle()
            .checkTitleIsDisplayed()
            .checkCheckButtonDisabled()
            .typeEmail("test@yavshok.ru")
            .checkCheckButtonEnabled()
    }

    @Test
    fun shouldDisableCheckButtonAfterClearingEmailOnMainScreen() {
        mainScreen
            .waitExistTitle()
            .typeEmail("")
            .checkCheckButtonDisabled()
    }

    @Test
    fun shouldShowEmailExistsStatusWithCorrectMessageAndGifOnMainScreen() {
        mainScreen
            .waitExistTitle()
            .checkEmailStatus("valerii.mrm@yandex.ru")
            .checkEmailExistsMessage()
    }

    @Test
    fun shouldShowEmailNotExistStatusWithCorrectMessageAndNoGifOnMainScreen() {
        mainScreen
            .checkEmailStatus("newuser@yavshok.ru")
            .checkEmailNotExistMessage()
    }
}