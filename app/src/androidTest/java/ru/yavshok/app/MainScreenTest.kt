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

    @get: Rule
    val composeRule = createComposeRule()

    private fun openMainScreen(){
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
    }

    @Test
    fun elementsOnMainScreenIsVisible() {
        openMainScreen()
//  Элементы отображены на дисплее
    composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
    composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
    composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
    composeRule.onNodeWithTag(Tags.MainScreen.checkButton).assertIsDisplayed()
}

    @Test
    fun shouldTypeEmailOnMainScreen() {
        openMainScreen()
        val testUser = TestDataId.registeredUser
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
//  Вводим в поле ввода данные зарег пользовтеля
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput(testUser.email)
//  Проверяем что текст ввелся и отображен на экране
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains(testUser.email)
        }

    @Test
    fun shouldCheckExistsEmailOnMainScreen() {
        openMainScreen()
        val testUser = TestDataId.registeredUser
//  Элементы отображены на дисплее
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
        composeRule.onNodeWithTag(Tags.MainScreen.confettiAnimation).assertIsDisplayed()

        }
    @Test
    fun shouldCheckNotExistsEmailOnMainScreen() {
        openMainScreen()
        val testUser = TestDataId.nonRegisteredUser
//  Элементы отображены на дисплее
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





