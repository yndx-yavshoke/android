package ru.yavshok.app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.qameta.allure.kotlin.junit4.DisplayName
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.screens.MainScreenPage
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.utils.TestData
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
    @DisplayName("Главная: проверка отображения всех UI элементов")
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
    @DisplayName("Главная: активация кнопки Проверить после ввода email")
    fun shouldEnableCheckButtonAfterTypingEmailOnMainScreen() {
        mainScreen
            .waitExistTitle()
            .checkTitleIsDisplayed()
            .checkCheckButtonDisabled()
            .typeEmail(TestData.NEW_EMAIL)
            .checkCheckButtonEnabled()
    }

    @Test
    @DisplayName("Главная: деактивация кнопки Проверить после очистки поля email")
    fun shouldDisableCheckButtonAfterClearingEmailOnMainScreen() {
        mainScreen
            .waitExistTitle()
            .typeEmail("")
            .checkCheckButtonDisabled()
    }

    @Test
    @DisplayName("Главная: кнопка Проверить неактивна при вводе некорректного email")
    fun shouldDisableCheckButtonForInvalidEmail() {
        mainScreen.waitExistTitle()
            .typeEmail(TestData.INVALID_EMAIL)
            .checkCheckButtonDisabled()
    }

    @Test
    @DisplayName("Главная: отображение статуса существующего email с правильным сообщением и GIF")
    fun shouldShowEmailExistsStatusWithCorrectMessageAndGifOnMainScreen() {
        mainScreen
            .waitExistTitle()
            .checkEmailStatus(TestData.EXISTING_EMAIL)
            .checkEmailExistsMessage()
    }

    @Test
    @DisplayName("Главная: отображение статуса несуществующего email с правильным сообщением без GIF")
    fun shouldShowEmailNotExistStatusWithCorrectMessageAndNoGifOnMainScreen() {
        mainScreen
            .waitExistTitle()
            .checkEmailStatus(TestData.NEW_EMAIL)
            .checkEmailNotExistMessage()
    }
}