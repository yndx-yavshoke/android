package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel


@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get: Rule
    val composeRule = createComposeRule()

    @Test
    fun shouldDisplayAllElementsOnMainScreen() {
        composeRule.setContent { MainScreen(
            onNavigateToLogin = {},
            viewModel = MainViewModel()
        ) }

        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.shockCheckButton).assertIsDisplayed()

        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains("Введите Email")
        composeRule.onNodeWithTag(Tags.MainScreen.shockCheckButton).assertIsNotEnabled()
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertTextEquals("Я в ШОКе")
    }

    @Test
    fun shouldTypeEmailOnMainScreen() {
        composeRule.setContent { MainScreen(
            onNavigateToLogin = {},
            viewModel = MainViewModel()
        ) }

        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput("a@ya.ru")

        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains("a@ya.ru")
        composeRule.onNodeWithTag(Tags.MainScreen.shockCheckButton).assertIsEnabled()

        //Thread.sleep(999999) //пауза

    }

    @Test
    fun shouldFindExistentUserOnMainScreen() {
        composeRule.setContent { MainScreen(
            onNavigateToLogin = {},
            viewModel = MainViewModel()
        ) }

        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput("a@ya.ru")

        composeRule.onNodeWithTag(Tags.MainScreen.shockCheckButton).assertIsEnabled()
        composeRule.onNodeWithTag(Tags.MainScreen.shockCheckButton).performClick()
        //Thread.sleep(5000)

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.successMessage)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains("a@ya.ru")
        composeRule.onNodeWithTag(Tags.MainScreen.successGif).assertIsDisplayed()
        //Thread.sleep(5000)

        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.shockCheckButton).assertIsDisplayed()

        //Thread.sleep(999999) //пауза
    }

    @Test
    fun shouldNotFindInexistentUserOnMainScreen() {
        composeRule.setContent { MainScreen(
            onNavigateToLogin = {},
            viewModel = MainViewModel()
        ) }

        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput("non-registered@ya.ru")

        composeRule.onNodeWithTag(Tags.MainScreen.shockCheckButton).assertIsEnabled()
        composeRule.onNodeWithTag(Tags.MainScreen.shockCheckButton).performClick()
        Thread.sleep(4999) //пауза

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.failMessage)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains("non-registered@ya.ru")
        Thread.sleep(4999) //пауза

        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.shockCheckButton).assertIsDisplayed()

        //Thread.sleep(999999) //пауза
    }
}