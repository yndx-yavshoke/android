package ru.yavshok.app

import android.app.Application
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
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
import ru.yavshok.app.viewmodel.LoginViewModel
import ru.yavshok.app.viewmodel.MainViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory

@ExperimentalTestApi
@RunWith(AndroidJUnit4::class)
class MainScreenTest {
//    Tags.MainScreen.screenTitle
//    Tags.MainScreen.emailTextField
//    Tags.MainScreen.checkButton
//    Tags.MainScreen.loginButton


    @get: Rule
    val composeRule = createComposeRule()

    @Test
    fun elementsOnMainScreenIsVisible() {
//  Наполняем экран страницей MainScreen
    composeRule.setContent {
        MainScreen(
            onNavigateToLogin = {},
            viewModel = MainViewModel()
        )
    }
    composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
    composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
    composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
    composeRule.onNodeWithTag(Tags.MainScreen.checkButton).assertIsDisplayed()
}

    @Test
    fun shouldTypeEmailOnMainScreen() {
        val testUser = TestDataId.registeredUser
//  Наполняем экран страницей MainScreen
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
//  Вводим в поле ввода данные зарег пользовтеля
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput(testUser.email)
//  Проверяем что текст ввелся и отображен на экране
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains(testUser.email)
        }

    @Test
    fun shouldCheckExistsEmailOnMainScreen() {
        val testUser = TestDataId.registeredUser
//  Наполняем экран страницей Мэйн
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
//  Вводим в поле ввода данные зарег пользовтеля
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput(testUser.email)
//  Проверяем что текст ввелся и отображен на экране
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains(testUser.email)
//  Кликаем на checkButton
        composeRule.onNodeWithTag(Tags.MainScreen.checkButton).performClick()
//  Ожидание прогрузки страницы
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.successMessageText))
//  Проверяем наличие successMessageImage и successMessageText
        composeRule.onNodeWithTag(Tags.MainScreen.successMessageImage).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.successMessageText).assertIsDisplayed()

        }
    @Test
    fun shouldCheckNotExistsEmailOnMainScreen() {
        val testUser = TestDataId.nonRegisteredUser
//  Наполняем экран страницей Мэйн
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
//  Вводим в поле ввода данные зарег пользовтеля
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput(testUser.email)
//  Проверяем что текст ввелся и отображен на экране
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains(testUser.email)
//  Кликаем на checkButton
        composeRule.onNodeWithTag(Tags.MainScreen.checkButton).performClick()
//  Ожидание прогрузки страницы
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.failMessageText))
//  Проверяем наличие failMessageText
        composeRule.onNodeWithTag(Tags.MainScreen.failMessageText).assertIsDisplayed()
    }

}





