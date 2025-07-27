package ru.yavshok.app.tests.MainScreenTests

import ru.yavshok.app.Tags
import ru.yavshok.app.rules.NavigationRule
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.helpers.assertTextDisplayed
import ru.yavshok.helpers.clickAndAssertExists

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class MainScreenNavigationTest: NavigationRule() {

    private val titleMain = Tags.MainScreen.screenTitle
    private val loginButton = Tags.MainScreen.loginButton

    private val titleLogin = Tags.LoginScreen.screenTitle

    @Before
    fun setup() {
        composeRule.assertTextDisplayed(titleMain, "Я в ШОКе")
    }

    @Test
    fun goToLoginScreenFromMainScreen() {
        composeRule.clickAndAssertExists(loginButton)

        composeRule.onNodeWithTag(loginButton).assertDoesNotExist()
        composeRule.assertTextDisplayed(titleLogin, "Войти в ШОК")
    }
}