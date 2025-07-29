package ru.yavshok.app.tests

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel
import org.junit.Before
import ru.yavshok.app.screens.MainScreen
import secrets.MyUser
import utils.User

class MainScreenTests {

    @get:Rule
    val composeRule = createComposeRule()
    private lateinit var mainScreen: MainScreen

    @Before
    fun setup() {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
        mainScreen = MainScreen(composeRule)
        mainScreen.assertScreenDisplayed()
    }

    @Test
    fun shouldViewGreenTextWithRegisterEmailOnMainScreen() {
        mainScreen.typeEmail(MyUser.EMAIL)
        mainScreen.clickOnImInShok()

        mainScreen.isInShok()
    }

    @Test
    fun shouldViewRedTextWithNoRegisterEmailOnMainScreen() {
        val user = User.generateRandomUser()
        mainScreen.typeEmail(user.email)
        mainScreen.clickOnImInShok()

        mainScreen.notIsInShok()
    }
}