package ru.yavshok.app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Fixtures.MainPage
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.utils_data.Users
import ru.yavshok.app.viewmodel.MainViewModel

@RunWith(AndroidJUnit4::class)


class MainPageTest {

    @get:Rule
    val composeRule = createComposeRule()

    private fun launchMainScreen() {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
    }


    val mainPage = MainPage(composeRule)


    @Test
    fun enterEmailOnMainScreen() {
        launchMainScreen()

        mainPage.assertTitleIsDisplayed()
            .assertLoginButtonIsDisplayed()
            .assertEmailFieldIsDisplayed()
            .enterEmail(Users.registeredUser.email)
            .assertEmailFieldContains(Users.registeredUser.email)
    }

    @Test
    fun checkRegisteredEmail() {
        launchMainScreen()

        mainPage
            .assertEmailFieldIsDisplayed()
            .enterEmail(Users.registeredUser.email)
            .assertEmailFieldContains(Users.registeredUser.email)
            .clickCheckButton()

        composeRule.waitUntil(timeoutMillis = 10_000) {
            composeRule.onAllNodesWithTag(Tags.MainScreen.EmailExistsTxt)
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        mainPage.assertSuccessTextIsDisplayed()
            .assertSuccessIMGIsDisplayed()
    }

    @Test
    fun checkUnregisteredEmail() {
        launchMainScreen()

        mainPage.assertEmailFieldIsDisplayed()
            .enterEmail(Users.unregisteredUser.email)
            .assertEmailFieldContains(Users.unregisteredUser.email)
            .clickCheckButton()

        composeRule.waitUntil(timeoutMillis = 10_000) {
            composeRule
                .onAllNodesWithText("Ты еще не в ШОКе")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
        mainPage.assertUnsuccessTextIsDisplayed()
    }

}


