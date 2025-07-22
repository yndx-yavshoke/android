package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel

@ExperimentalTestApi
@RunWith(AndroidJUnit4::class)
class MainScreenNavigationTest {

    //Tags.MainScreen.screenTitle
    //Tags.MainScreen.loginButton
    //Tags.LoginScreen.screenTitle
    //Tags.LoginScreen.checkButton

    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shouldNavigateFromMainScreenToLoginScreen() {
//  Наполняем экран страницей MainScreen
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )

//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
//  Клиеаем по кнопке "В шок"
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
//  Ожидание прогрузки страницы
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )
//  Проверяем наличие титула на странице LoginScreen
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
//  Проверяем что MainScreen исчез
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertDoesNotExist()
    }

}