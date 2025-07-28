package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.screens.LoginScreenPage
import ru.yavshok.app.fixtures.screens.MainScreenPage

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class NavigationMainToLoginTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val mainScreen by lazy { MainScreenPage(composeRule) }
    private val loginScreen by lazy { LoginScreenPage(composeRule) }

    @Test
    fun shouldNavigateFromMainScreenToLoginScreen() {
        mainScreen
            .waitExistTitle()
            .checkTitleIsDisplayed()
            .clickLogin()

        loginScreen
            .waitExistTitle()
            .checkTitleIsDisplayed()

        mainScreen.title.assertDoesNotExist()
    }
}