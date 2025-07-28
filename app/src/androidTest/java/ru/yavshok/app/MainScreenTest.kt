package ru.yavshok.app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.screens.MainScreen
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var mainScreen: MainScreen

    val vmFactory = ViewModelFactory(getApplicationContext())

    @Before
    fun setUp(){
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }

        mainScreen = MainScreen(composeRule)
    }

    @Test
    fun shouldDisplayedAllViewMainScreen() {
        with(mainScreen) {
            waitToLoad()
            checkEmailFieldIsDisplayed()
            checkCheckButtonIsDisplayed()
            checkCheckButtonIsDisplayed()
        }
    }

    @Test
    fun shouldShowInShockLabelOnMainPage() {
        val email = "testuser@domain.com"

        with(mainScreen) {
            checkEmailFieldIsDisplayed()
            typeEmail(email)
            checkTypedEmail(email)
            checkCheckButtonIsDisplayed()
            tapCheckButton()
            waitToLoadLabel()
            checkSuccessLabelIsDisplayed()
        }
    }

    @Test
    fun shouldShowSuccessCatOnMainPage() {
        val email = "testuser@domain.com"

        with(mainScreen) {
            checkEmailFieldIsDisplayed()
            typeEmail(email)
            checkTypedEmail(email)
            checkCheckButtonIsDisplayed()
            tapCheckButton()
            waitToLoadLabel()
            checkSuccessCatIsDisplayed()
        }
    }
}